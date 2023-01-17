package com.tccparkingiot.api.exceptions.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.tccparkingiot.api.exceptions.EntityInUseException;
import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String MSG_INTERNAL_SERVER_ERROR =
            "Ocorreu um erro interno no Servidor. " +
                    "Tente novamente, caso o erro persista entre em contato com o administrador";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(Exception ex, WebRequest request){
         HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.INTERNAL_SERVER_ERROR;
        String detail = ex.getMessage();
        ex.printStackTrace();
        Problem problem = createProblemBuilder(status,problemType,detail).build();
        return handleExceptionInternal(ex,problem,new HttpHeaders(),status,request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    private ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex, WebRequest request){
        List<Problem.Field> problemFields = ex.getConstraintViolations().stream()
                .map(field -> Problem.Field.builder()
                        .name(field.getPropertyPath().toString())
                        .userMessage(field.getMessage())
                        .build())
                .collect(Collectors.toList());

        var fieldErros = joinField(problemFields);

        String detail = String.format("Campo(s) '%s' inválido(s)", fieldErros);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = createProblemBuilder(status, ProblemType.INVALID_ATTRIBUTE_ERROR, detail)
                .fields(problemFields)
                .userMessage("Verifique os campos inválidos")
                .build();

        return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        Problem problem = createProblemBuilder(status,ProblemType.INVALID_ATTRIBUTE_ERROR, ex.getMessage())
                .userMessage("Atributo inválido")
                .build();


        return handleExceptionInternal(ex,problem,headers,status,request);
    }

    private ResponseEntity<Object> handlePropertyBinding
            (PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.ERROR_NONEXISTENT_PROPERTY;
        String detail = String.format("A propriedade '%s' não existe.",
                ex.getPath().get(0).getFieldName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage("Remova a propriedade inexistente e tente novamente")
                .build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat
            (InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        ProblemType problemType = ProblemType.ERROR_UNREADABLE_MESSAGE;

        String detail = String.format
                ("A propriedade '%s' recebeu um valor imcompatível. Ajuste e tente novamente",ex.getPath().toString());

        Problem problem = createProblemBuilder(status,problemType, detail)
                .userMessage("handle invalid format")
                .build();

        return handleExceptionInternal(ex,problem,headers,status,request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.ERROR_RESOURCE_NOT_FOUND;
        String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.", ex.getRequestURL());

        Problem problem = createProblemBuilder(status,problemType,detail)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex, WebRequest  request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        ProblemType problemType = ProblemType.ERROR_RESOURCE_NOT_FOUND;

        Problem problem = createProblemBuilder(status,problemType, detail).build();
        return handleExceptionInternal((EntityNotFoundException)ex, problem,new HttpHeaders(),status,request);
    }

    @ExceptionHandler(EntityInUseException.class)
    private ResponseEntity<?> handleEntityInUseException(EntityInUseException ex, WebRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        String detail = ex.getMessage();
        ProblemType problemType = ProblemType.ERROR_RESOURCE_IN_USE;

        Problem problem = createProblemBuilder(status,problemType, detail).build();
        return handleExceptionInternal((EntityInUseException)ex, problem,new HttpHeaders(),status,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var bindingResult = ex.getBindingResult();
        List<Problem.Field> problemFields = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> Problem.Field.builder()
                        .name(fieldError.getField())
                        .userMessage(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        var fieldErros = joinField(problemFields);

        String detail = String.format("Campo(s) '%s' está/estão inválido(s)", fieldErros);

        Problem problem = createProblemBuilder(status, ProblemType.INVALID_ATTRIBUTE_ERROR, detail)
                .fields(problemFields)
                .userMessage("Verifique os campos inválidos")
                .build();

        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }



    @Override
    protected ResponseEntity<Object> handleExceptionInternal
            (Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(body == null){
            body = Problem.builder()
                    .timeStamp(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        }else if(body instanceof String){
            body = Problem.builder()
                    .timeStamp(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }


    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail){
        return Problem.builder()
                .timeStamp(LocalDateTime.now())
                .detail(detail)
                .status(status.value())
                .title(problemType.getTitle());
    }

    private String joinField(List<Problem.Field> list){
        return list.stream()
                .map(Problem.Field::getName)
                .collect(Collectors.joining(" e "));
    }


}

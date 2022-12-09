package com.tccparkingiot.api.exceptions.handler;

import com.tccparkingiot.api.exceptions.EntityInUseException;
import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String MSG_INTERNAL_SERVER_ERROR =
            "Ocorreu um erro interno no Servidor. Tente novamente, caso o erro persista entre em contato com o administrador";

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

        String detail = String.format("Campo(s) '%s' está/estão inválido(s)", fieldErros);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = createProblemBuilder(status, ProblemType.INVALID_ATTRIBUTE_ERROR, detail)
                .fields(problemFields)
                .userMessage("Verifique os campos inválidos")
                .build();

        return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
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
                .collect(Collectors.joining(" and "));
    }


}

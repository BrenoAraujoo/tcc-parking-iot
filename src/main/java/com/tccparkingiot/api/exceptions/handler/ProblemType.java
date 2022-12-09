package com.tccparkingiot.api.exceptions.handler;


import lombok.Getter;

@Getter
public enum ProblemType {
    ERROR_RESOURCE_NOT_FOUND("Recurso não encontrado"),
    ERROR_RESOURCE_IN_USE("Recurso em uso"),
    INTERNAL_SERVER_ERROR("Erro interno de servidor"),
    INVALID_ATTRIBUTE_ERROR("Erro de atributo inválido");

    private String title;

    ProblemType(String title) {
        this.title = title;
    }
}

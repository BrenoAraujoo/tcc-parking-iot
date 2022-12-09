package com.tccparkingiot.api.exceptions.handler;


import lombok.Getter;

@Getter
public enum ProblemType {
    ERROR_RESOURCE_NOT_FOUND("Recurso não encontrado"),
    ERROR_RESOURCE_IN_USE("Recurso em uso"),
    INTERNAL_SERVER_ERROR("Erro interno de servidor"),
    INVALID_ATTRIBUTE_ERROR("Erro de atributo inválido"),
    ERROR_NULL_ATTRIBUTE("Erro atributo nulo"),
    ERROR_UNREADABLE_MESSAGE("Mensagem ilegível"),
    ERROR_NONEXISTENT_PROPERTY("Propriedade inexistente");

    private String title;

    ProblemType(String title) {
        this.title = title;
    }
}

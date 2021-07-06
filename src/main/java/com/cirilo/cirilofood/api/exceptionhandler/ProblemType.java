package com.cirilo.cirilofood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_PARAMETER("/parametro-invalido","Parâmetro inválido"),
    MESSAGE_NOT_READABLE("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTITY_IN_USE("/entidade-em-uso", "Entidade em uso"),
    BUSINESS_ERROR("/erro-de-negocio", "Violação de regra de negócio"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");

    private String title;

    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://cirilofood.com.br" + path;
        this.title = title;
    }
}

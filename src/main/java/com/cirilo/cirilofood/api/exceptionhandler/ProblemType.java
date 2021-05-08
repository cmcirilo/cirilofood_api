package com.cirilo.cirilofood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade nao encontrada");

    private String title;

    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://cirilofood.com.br" + path;
        this.title = title;
    }
}

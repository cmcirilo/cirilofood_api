package com.cirilo.cirilofood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_DE_NEGOCIO("/erro-de-negocio", "Violação de regra de negócio");

    private String title;

    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://cirilofood.com.br" + path;
        this.title = title;
    }
}

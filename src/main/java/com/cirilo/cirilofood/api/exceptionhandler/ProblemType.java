package com.cirilo.cirilofood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    PARAMETRO_INVALIDO("/parametro-invalido","Parâmetro inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_DE_NEGOCIO("/erro-de-negocio", "Violação de regra de negócio"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");

    private String title;

    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://cirilofood.com.br" + path;
        this.title = title;
    }
}

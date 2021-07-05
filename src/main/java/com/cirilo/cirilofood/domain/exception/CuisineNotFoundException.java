package com.cirilo.cirilofood.domain.exception;

public class CuisineNotFoundException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CuisineNotFoundException(String mensagem) {
        super(mensagem);
    }

    public CuisineNotFoundException(Long cuisineId) {
        this(String.format("Not Exists cuisine with id %d", cuisineId));
    }
}

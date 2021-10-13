package com.cirilo.cirilofood.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String code) {
        super(String.format("Not exists order with code %d", code));
    }

}

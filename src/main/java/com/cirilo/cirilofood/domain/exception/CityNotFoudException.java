package com.cirilo.cirilofood.domain.exception;

public class CityNotFoudException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CityNotFoudException(String message) {
        super(message);
    }

    public CityNotFoudException(Long cityId) {
        this(String.format("Not exists city with id %d", cityId));
    }

}
package com.cirilo.cirilofood.domain.exception;

public class CuisineNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CuisineNotFoundException(String message) {
        super(message);
    }

    public CuisineNotFoundException(Long cuisineId) {
        this(String.format("Not Exists cuisine with id %d", cuisineId));
    }
}

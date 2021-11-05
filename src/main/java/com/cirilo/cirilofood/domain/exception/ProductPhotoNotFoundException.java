package com.cirilo.cirilofood.domain.exception;

public class ProductPhotoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public ProductPhotoNotFoundException(String message) {
        super(message);
    }

    public ProductPhotoNotFoundException(Long restaurantId, Long productId ) {
        this(String.format("Not exists product photo with id %d for restaurant with id %d", productId, restaurantId));
    }
}

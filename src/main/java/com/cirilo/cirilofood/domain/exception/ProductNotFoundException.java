package com.cirilo.cirilofood.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ProductNotFoundException(Long restaurantId, Long productId) {
        this(String.format("Not exists product with id %d for the restaurant with id %d",
                productId, restaurantId));
    }
}

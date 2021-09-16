package com.cirilo.cirilofood.domain.exception;

public class FormPaymentNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public FormPaymentNotFoundException(String message) {
        super(message);
    }

    public FormPaymentNotFoundException(Long formPaymentId) {
        this(String.format("Not exists form payment with id %d", formPaymentId));
    }
}

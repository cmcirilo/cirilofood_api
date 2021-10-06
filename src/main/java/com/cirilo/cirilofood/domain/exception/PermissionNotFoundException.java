package com.cirilo.cirilofood.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long permissionId) {
        this(String.format("Not exists permission with id %d", permissionId));
    }
}

package com.cirilo.cirilofood.domain.exception;

public class GroupNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(Long groupId) {
        this(String.format("Not exists group with id %d", groupId));
    }

}

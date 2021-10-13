package com.cirilo.cirilofood.domain.model;

public enum StatusOrder {

    CREATED("Created"),
    CONFIRMED("Confirmed"),
    DELIVERED("Delivered"),
    CANCELED("Canceled");

    private String description;

    StatusOrder(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}

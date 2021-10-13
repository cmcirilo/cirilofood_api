package com.cirilo.cirilofood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusOrder {

    CREATED("Created"),
    CONFIRMED("Confirmed", CREATED),
    DELIVERED("Delivered", CONFIRMED),
    CANCELED("Canceled", CREATED);

    private final String description;

    private final List<StatusOrder> previousStatus;

    StatusOrder(String description, StatusOrder... previousStatus) {
        this.description = description;
        this.previousStatus = Arrays.asList(previousStatus);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean shoudNotUpdateTo(StatusOrder newStatus) {
        return !newStatus.previousStatus.contains(this);
    }
}

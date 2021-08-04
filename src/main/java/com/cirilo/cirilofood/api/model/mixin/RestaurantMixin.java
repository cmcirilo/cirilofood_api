package com.cirilo.cirilofood.api.model.mixin;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cirilo.cirilofood.domain.model.Address;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.model.FormPayment;
import com.cirilo.cirilofood.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class RestaurantMixin {

    @JsonIgnore
    private final List<FormPayment> formsPayment = new ArrayList<>();

    @JsonIgnore
    private final List<Product> products = new ArrayList<>();

    @JsonIgnoreProperties(value = "name", allowGetters = true) // when setName throw exception also getName is OK.
    private Cuisine cuisine;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private OffsetDateTime createdDate;

    @JsonIgnore
    private OffsetDateTime updatedDate;

}

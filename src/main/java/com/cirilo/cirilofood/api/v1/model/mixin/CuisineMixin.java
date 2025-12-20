package com.cirilo.cirilofood.api.v1.model.mixin;

import com.cirilo.cirilofood.domain.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class CuisineMixin {

    @JsonIgnore
    private List<Restaurant> restaurants;
}

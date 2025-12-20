package com.cirilo.cirilofood.api.v1.model.mixin;

import com.cirilo.cirilofood.domain.model.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CityMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;
}

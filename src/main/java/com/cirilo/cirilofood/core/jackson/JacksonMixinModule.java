package com.cirilo.cirilofood.core.jackson;

import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.mixin.CityMixin;
import com.cirilo.cirilofood.api.model.mixin.CuisineMixin;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(City.class, CityMixin.class);
        setMixInAnnotation(Cuisine.class, CuisineMixin.class);
    }
}

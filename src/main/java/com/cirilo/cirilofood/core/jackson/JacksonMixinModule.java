package com.cirilo.cirilofood.core.jackson;

import com.cirilo.cirilofood.domain.model.Restaurant;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.mixin.RestaurantMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
    }
}

package com.cirilo.cirilofood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.input.RestaurantInput;
import com.cirilo.cirilofood.domain.model.Restaurant;

@Component
public class RestaurantInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);
    }
}

package com.cirilo.cirilofood.api.v1.assembler;

import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.model.Cuisine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.model.input.RestaurantInput;
import com.cirilo.cirilofood.domain.model.Restaurant;

@Component
public class RestaurantInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

    public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant){
        restaurant.setCuisine(new Cuisine());

        if (restaurant.getAddress() != null){
            restaurant.getAddress().setCity(new City());
        }

        modelMapper.map(restaurantInput,restaurant);
    }
}

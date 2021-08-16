package com.cirilo.cirilofood.api.assembler;

import com.cirilo.cirilofood.api.model.input.RestaurantInput;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        Restaurant restaurant = new Restaurant();

        restaurant.setName(restaurantInput.getName());
        restaurant.setShippingFee(restaurantInput.getShippingFee());

        Cuisine cuisine = new Cuisine();
        cuisine.setId(restaurantInput.getCuisine().getId());

        restaurant.setCuisine(cuisine);

        return restaurant;
    }
}

package com.cirilo.cirilofood.api.assembler;

import com.cirilo.cirilofood.api.model.CuisineModel;
import com.cirilo.cirilofood.api.model.RestaurantModel;
import com.cirilo.cirilofood.domain.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantModelAssembler { // class to convert entity to DTO

    public RestaurantModel toModel(Restaurant restaurant) {
        CuisineModel cuisineModel = new CuisineModel();
        cuisineModel.setId(restaurant.getCuisine().getId());
        cuisineModel.setName(restaurant.getCuisine().getName());

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(restaurant.getId());
        restaurantModel.setName(restaurant.getName());
        restaurantModel.setShippingFee(restaurant.getShippingFee());
        restaurantModel.setCuisine(cuisineModel);
        return restaurantModel;
    }

    public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

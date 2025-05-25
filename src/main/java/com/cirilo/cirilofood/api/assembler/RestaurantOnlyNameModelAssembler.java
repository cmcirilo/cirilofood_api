package com.cirilo.cirilofood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.CiriloLinks;
import com.cirilo.cirilofood.api.controller.RestaurantController;
import com.cirilo.cirilofood.api.model.RestaurantOnlyNameModel;
import com.cirilo.cirilofood.domain.model.Restaurant;

@Component
public class RestaurantOnlyNameModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantOnlyNameModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public RestaurantOnlyNameModelAssembler() {
        super(RestaurantController.class, RestaurantOnlyNameModel.class);
    }

    @Override
    public RestaurantOnlyNameModel toModel(Restaurant restaurant) {
        RestaurantOnlyNameModel restaurantOnlyNameModel = createModelWithId(
                restaurant.getId(), restaurant);

        modelMapper.map(restaurant, restaurantOnlyNameModel);

        restaurantOnlyNameModel.add(ciriloLinks.linkToRestaurants("restaurants"));

        return restaurantOnlyNameModel;
    }

    @Override
    public CollectionModel<RestaurantOnlyNameModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities)
                .add(ciriloLinks.linkToRestaurants());
    }

}

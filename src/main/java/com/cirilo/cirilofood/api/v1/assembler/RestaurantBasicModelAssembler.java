package com.cirilo.cirilofood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.controller.RestaurantController;
import com.cirilo.cirilofood.api.v1.model.RestaurantBasicModel;
import com.cirilo.cirilofood.core.security.CiriloSecurity;
import com.cirilo.cirilofood.domain.model.Restaurant;

@Component
public class RestaurantBasicModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantBasicModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    @Autowired
    private CiriloSecurity ciriloSecurity;

    public RestaurantBasicModelAssembler() {
        super(RestaurantController.class, RestaurantBasicModel.class);
    }

    @Override
    public RestaurantBasicModel toModel(Restaurant restaurant) {
        RestaurantBasicModel restaurantBasicModel = createModelWithId(
                restaurant.getId(), restaurant);

        modelMapper.map(restaurant, restaurantBasicModel);

        if (ciriloSecurity.allowListRestaurants()) {
            restaurantBasicModel.add(ciriloLinks.linkToRestaurants("restaurants"));
        }

        if (ciriloSecurity.allowListCuisines()) {
            restaurantBasicModel.getCuisine().add(
                    ciriloLinks.linkToCuisine(restaurant.getCuisine().getId()));
        }

        return restaurantBasicModel;
    }

    @Override
    public CollectionModel<RestaurantBasicModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantBasicModel> collectionModel = super.toCollectionModel(entities);

        if (ciriloSecurity.allowListRestaurants()) {
            collectionModel.add(ciriloLinks.linkToRestaurants());
        }

        return collectionModel;
    }

}

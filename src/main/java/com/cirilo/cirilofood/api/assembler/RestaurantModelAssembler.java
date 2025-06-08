package com.cirilo.cirilofood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.CiriloLinks;
import com.cirilo.cirilofood.api.controller.RestaurantController;
import com.cirilo.cirilofood.api.model.RestaurantModel;
import com.cirilo.cirilofood.domain.model.Restaurant;

@Component
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public RestaurantModelAssembler() {
        super(RestaurantController.class, RestaurantModel.class);
    }

    public RestaurantModel toModel(Restaurant restaurant) {
        RestaurantModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantModel);

        restaurantModel.add(ciriloLinks.linkToRestaurants("restaurants"));

        restaurantModel.getCuisine().add(
                ciriloLinks.linkToCuisine(restaurant.getCuisine().getId()));

        restaurantModel.getAddress().getCity().add(
                ciriloLinks.linkToCuisine(restaurant.getAddress().getCity().getId()));

        restaurantModel.add(ciriloLinks.linkToRestaurantFormsPayment(restaurant.getId(),
                "forms-payment"));

        restaurantModel.add(ciriloLinks.linkToOwnersRestaurant(restaurant.getId(),
                "owners"));

        if (restaurant.activateAllowed()) {
            restaurantModel.add(
                    ciriloLinks.linkToRestaurantActivate(restaurant.getId(), "activate"));
        }

        if (restaurant.desactivateAllowed()) {
            restaurantModel.add(
                    ciriloLinks.linkToRestaurantDesactivate(restaurant.getId(), "desactivate"));
        }

        if (restaurant.openAllowed()) {
            restaurantModel.add(
                    ciriloLinks.linkToRestaurantOpen(restaurant.getId(), "open"));
        }

        if (restaurant.closeAllowed()) {
            restaurantModel.add(
                    ciriloLinks.linkToRestaurantClose(restaurant.getId(), "close"));
        }

        return restaurantModel;
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities)
                .add(ciriloLinks.linkToRestaurants());
    }
}

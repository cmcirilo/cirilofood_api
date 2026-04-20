package com.cirilo.cirilofood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.controller.RestaurantController;
import com.cirilo.cirilofood.api.v1.model.RestaurantModel;
import com.cirilo.cirilofood.core.security.CiriloSecurity;
import com.cirilo.cirilofood.domain.model.Restaurant;

@Component
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    @Autowired
    private CiriloSecurity ciriloSecurity;

    public RestaurantModelAssembler() {
        super(RestaurantController.class, RestaurantModel.class);
    }

    @Override
    public RestaurantModel toModel(Restaurant restaurant) {

        RestaurantModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantModel);

        if (ciriloSecurity.allowListRestaurants()) {
            restaurantModel.add(ciriloLinks.linkToRestaurants("restaurants"));
        }

        if (ciriloSecurity.allowManageRegistrationRestaurant()) {
            if (restaurant.activateAllowed()) {
                restaurantModel.add(
                        ciriloLinks.linkToRestaurantActivate(restaurant.getId(), "activate"));
            }

            if (restaurant.desactivateAllowed()) {
                restaurantModel.add(
                        ciriloLinks.linkToRestaurantDesactivate(restaurant.getId(), "desactivate"));
            }
        }

        if (ciriloSecurity.allowManageOperationRestaurant(restaurant.getId())) {
            if (restaurant.openAllowed()) {
                restaurantModel.add(
                        ciriloLinks.linkToRestaurantOpen(restaurant.getId(), "open"));
            }

            if (restaurant.closeAllowed()) {
                restaurantModel.add(
                        ciriloLinks.linkToRestaurantClose(restaurant.getId(), "close"));
            }
        }

        if (ciriloSecurity.allowListRestaurants()) {
            restaurantModel.add(ciriloLinks.linkToProducts(restaurant.getId(), "products"));
        }

        if (ciriloSecurity.allowListCuisines()) {
            restaurantModel.getCuisine().add(
                    ciriloLinks.linkToCuisine(restaurant.getCuisine().getId()));
        }

        if (ciriloSecurity.allowListCities()) {
            if (restaurantModel.getAddress() != null
                    && restaurantModel.getAddress().getCity() != null) {
                restaurantModel.getAddress().getCity().add(
                        ciriloLinks.linkToCity(restaurant.getAddress().getCity().getId()));
            }
        }

        if (ciriloSecurity.allowListRestaurants()) {
            restaurantModel.add(ciriloLinks.linkToRestaurantFormsPayment(restaurant.getId(),
                    "forms-payment"));
        }

        if (ciriloSecurity.allowManageRegistrationRestaurant()) {
            restaurantModel.add(ciriloLinks.linkToOwnersRestaurant(restaurant.getId(),
                    "owners"));
        }

        return restaurantModel;
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantModel> collectionModel = super.toCollectionModel(entities);

        if (ciriloSecurity.allowListRestaurants()) {
            collectionModel.add(ciriloLinks.linkToRestaurants());
        }

        return collectionModel;
    }
}

package com.cirilo.cirilofood.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.cirilo.cirilofood.api.CiriloLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.assembler.UserModelAssembler;
import com.cirilo.cirilofood.api.model.UserModel;
import com.cirilo.cirilofood.api.openapi.controller.RestaurantOwnerControllerOpenApi;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.service.RestaurantService;

@RestController
@RequestMapping(path = "/restaurants/{restaurantId}/owners", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantOwnerController implements RestaurantOwnerControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private CiriloLinks ciriloLinks;

    @GetMapping
    public CollectionModel<UserModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        CollectionModel<UserModel> usersModel = userModelAssembler
                .toCollectionModel(restaurant.getOwners())
                .removeLinks()
                .add(ciriloLinks.linkToOwnersRestaurant(restaurantId))
                .add(ciriloLinks.linkToRestaurantOwnerAssociate(restaurantId, "associate"));

        usersModel.getContent().stream().forEach(userModel -> {
            userModel.add(ciriloLinks.linkToRestaurantOwnerDisassociate(
                    restaurantId, userModel.getId(), "disassociate"));
        });

        return usersModel;

    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateOwner(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateOwner(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }
}

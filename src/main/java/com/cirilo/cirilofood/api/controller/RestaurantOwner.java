package com.cirilo.cirilofood.api.controller;

import com.cirilo.cirilofood.api.assembler.UserModelAssembler;
import com.cirilo.cirilofood.api.model.UserModel;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/owners")
public class RestaurantOwner {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @GetMapping
    public List<UserModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        return userModelAssembler.toCollectionModel(restaurant.getOwners());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateOwner(restaurantId, userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateOwner(restaurantId, userId);
    }
}
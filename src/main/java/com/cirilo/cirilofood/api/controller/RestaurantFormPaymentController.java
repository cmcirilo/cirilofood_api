package com.cirilo.cirilofood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.assembler.FormPaymentModelAssembler;
import com.cirilo.cirilofood.api.model.FormPaymentModel;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.service.RestaurantService;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/forms-payment")
public class RestaurantFormPaymentController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FormPaymentModelAssembler formPaymentModelAssembler;

    @GetMapping
    public List<FormPaymentModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        return formPaymentModelAssembler.toCollectioModel(restaurant.getFormsPayment());
    }

    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateFormPayment(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.disassociateFormPayment(restaurantId, formPaymentId);
    }

    @PutMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateFormPayment(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.associateFormPayment(restaurantId, formPaymentId);
    }

}

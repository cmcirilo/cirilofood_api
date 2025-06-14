package com.cirilo.cirilofood.api.controller;

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

import com.cirilo.cirilofood.api.CiriloLinks;
import com.cirilo.cirilofood.api.assembler.FormPaymentModelAssembler;
import com.cirilo.cirilofood.api.model.FormPaymentModel;
import com.cirilo.cirilofood.api.openapi.controller.RestaurantFormPaymentControllerOpenApi;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.service.RestaurantService;

@RestController
@RequestMapping(path = "/restaurants/{restaurantId}/forms-payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantFormPaymentController implements RestaurantFormPaymentControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FormPaymentModelAssembler formPaymentModelAssembler;

    @Autowired
    private CiriloLinks ciriloLinks;

    @GetMapping
    public CollectionModel<FormPaymentModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        CollectionModel<FormPaymentModel> formsPaymentModel = formPaymentModelAssembler.toCollectionModel(restaurant.getFormsPayment())
                .removeLinks()
                .add(ciriloLinks.linkToRestaurantFormsPayment(restaurantId));

        formsPaymentModel.getContent().forEach(formPaymentModel -> {
            formPaymentModel.add(ciriloLinks.linkToRestaurantFormPaymentDisassociate(restaurantId, formPaymentModel.getId(), "disassociate"));
        });

        return formsPaymentModel;
    }

    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociateFormPayment(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.disassociateFormPayment(restaurantId, formPaymentId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateFormPayment(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.associateFormPayment(restaurantId, formPaymentId);
    }

}

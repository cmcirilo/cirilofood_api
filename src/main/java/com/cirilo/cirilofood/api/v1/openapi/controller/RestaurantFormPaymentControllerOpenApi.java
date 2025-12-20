package com.cirilo.cirilofood.api.v1.openapi.controller;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.v1.model.FormPaymentModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurants")
public interface RestaurantFormPaymentControllerOpenApi {

    @ApiOperation("List forms payment associated with restaurant")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    CollectionModel<FormPaymentModel> list(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Disassociate restaurant with form payment")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Disassociation confirmed with success"),
        @ApiResponse(code = 404, message = "Restaurant or form payment not found",
                response = Problem.class)
    })
    ResponseEntity<Void> disassociateFormPayment(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(value = "Form Payment Id", example = "1", required = true) Long formPaymentId);

    @ApiOperation("Associate restaurant with form payment")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Association confirmed with success"),
        @ApiResponse(code = 404, message = "Restaurant or form payment not found",
                response = Problem.class)
    })
    ResponseEntity<Void> associateFormPayment(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(value = "Form Payment Id", example = "1", required = true) Long formPaymentId);
}

package com.cirilo.cirilofood.api.openapi.controller;

import java.util.List;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.UserModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurants")
public interface RestaurantOwnerControllerOpenApi {

    @ApiOperation("List restaurants owners")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    CollectionModel<UserModel> list(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Disassociate restaurant owner")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Disassociation with success"),
        @ApiResponse(code = 404, message = "Restaurant or user not found",
                response = Problem.class)
    })
    ResponseEntity<Void> disassociate(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
                                      @ApiParam(value = "User Id", example = "1", required = true) Long userId);

    @ApiOperation("Associate restaurant owner")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Association with success"),
        @ApiResponse(code = 404, message = "Restaurant or user not found",
                response = Problem.class)
    })
    ResponseEntity<Void> associate(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(value = "User Id", example = "1", required = true) Long userId);
}

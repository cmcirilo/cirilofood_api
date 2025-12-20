package com.cirilo.cirilofood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.v1.model.RestaurantBasicModel;
import com.cirilo.cirilofood.api.v1.model.RestaurantModel;
import com.cirilo.cirilofood.api.v1.model.RestaurantOnlyNameModel;
import com.cirilo.cirilofood.api.v1.model.input.RestaurantInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Restaurants")
public interface RestaurantControllerOpenApi {

    @ApiOperation(value = "List restaurants")
    @ApiImplicitParams({
        @ApiImplicitParam(value = "Name of projection orders", allowableValues = "only-name",
                name = "projection", paramType = "query", type = "string")
    })
    // @JsonView(RestaurantView.Resume.class)
    CollectionModel<RestaurantBasicModel> list();

    @ApiIgnore
    @ApiOperation(value = "List restaurants", hidden = true)
    CollectionModel<RestaurantModel> listComplete();

    @ApiIgnore
    @ApiOperation(value = "List restaurants", hidden = true)
    // @JsonView(RestaurantView.OnlyName.class)
    CollectionModel<RestaurantOnlyNameModel> listOnlyName();

    @ApiOperation("Find restaurant by Id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Restaurant Id invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    RestaurantModel find(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Create new restaurant")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Restaurant created"),
    })
    RestaurantModel create(
            @ApiParam(name = "body", value = "Representation of new restaurant", required = true) RestaurantInput restaurantInput);

    @ApiOperation("Update restaurant by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Restaurant updated"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    RestaurantModel update(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(name = "body", value = "Representation of  restaurant with new data",
                    required = true) RestaurantInput restaurantInput);

    @ApiOperation("Activate restaurant by Id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante activated with success"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    ResponseEntity<Void> activate(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Inactivate restaurant by Id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurant inactivated with success"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    ResponseEntity<Void> desactivate(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Open restaurant by Id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurant opened with success"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    ResponseEntity<Void> open(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Close restaurant by Id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurant closed with success"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    ResponseEntity<Void> close(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Activate multiples restaurants")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurants activated with success")
    })
    void activations(@ApiParam(name = "body", value = "Restaurants Ids", required = true) List<Long> restaurantIds);

    @ApiOperation("Inactivate multiples restaurants")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurants inactivated with success")
    })
    void desactivations(@ApiParam(name = "body", value = "Restaurants Ids", required = true) List<Long> restaurantIds);
}

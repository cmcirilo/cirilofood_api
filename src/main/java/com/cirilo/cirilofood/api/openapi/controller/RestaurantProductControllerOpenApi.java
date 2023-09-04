package com.cirilo.cirilofood.api.openapi.controller;

import java.util.List;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.ProductModel;
import com.cirilo.cirilofood.api.model.input.ProductInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Products")
public interface RestaurantProductControllerOpenApi {

    @ApiOperation("List restaurant's products")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Restaurant Id invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    List<ProductModel> list(
            @ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(value = "Indicate that should or not include inactive products in list", example = "false", required = true) boolean includeInactives);

    @ApiOperation("Find restaurant's product")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Restaurant Id or product invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "Restaurant's product not found", response = Problem.class)
    })
    ProductModel find(
            @ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(value = "Product Id", example = "1", required = true) Long productId);

    @ApiOperation("Create restaurant's product")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Restaurant's product created"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = Problem.class)
    })
    ProductModel create(
            @ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(name = "body", value = "Representation of new restaurant's product", required = true) ProductInput productInput);

    @ApiOperation("Update restaurant's product")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Restaurant's product updated"),
        @ApiResponse(code = 404, message = "Restaurant's product not found", response = Problem.class)
    })
    ProductModel update(
            @ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(value = "Product Id", example = "1", required = true) Long productId,
            @ApiParam(name = "body", value = "Representation of new restaurant's product", required = true) ProductInput productInput);
}

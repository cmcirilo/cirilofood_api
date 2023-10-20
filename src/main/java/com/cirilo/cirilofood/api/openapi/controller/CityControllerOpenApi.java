package com.cirilo.cirilofood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.CityModel;
import com.cirilo.cirilofood.api.model.input.CityInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cities")
public interface CityControllerOpenApi {

    @ApiOperation("List Cities")
    CollectionModel<CityModel> list();

    @ApiOperation("Find City by Id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "City Id invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "City Id not found", response = Problem.class)
    })
    CityModel find(@ApiParam(value = "City Id", example = "1", required = true) Long cityId);

    @ApiOperation("Create City")
    @ApiResponses({
        @ApiResponse(code = 201, message = "City created")
    })
    CityModel create(@ApiParam(name = "body", value = "Representation of new city", required = true) CityInput cityInput);

    @ApiOperation("Update City by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "City updated"),
        @ApiResponse(code = 404, message = "City Id not found", response = Problem.class)
    })
    CityModel update(@ApiParam(value = "City Id", example = "1", required = true) Long cityId,
            @ApiParam(name = "body", value = "Representation of updated city", required = true) CityInput cityInput);

    @ApiOperation("Delete City by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "City deleted"),
        @ApiResponse(code = 404, message = "City Id not found", response = Problem.class)
    })
    void delete(@ApiParam(value = "City Id", example = "1", required = true) Long cityId);
}

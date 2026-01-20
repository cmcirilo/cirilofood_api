package com.cirilo.cirilofood.api.v2.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.v2.model.CuisineModelV2;
import com.cirilo.cirilofood.api.v2.model.input.CuisineInputV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cuisines")
public interface CuisineControllerV2OpenApi {

    @ApiOperation("List cuisines on pagination")
    PagedModel<CuisineModelV2> list(Pageable pageable);

    @ApiOperation("Get cuisine by Id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Cuisine Id invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "Cuisine not found", response = Problem.class)
    })
    CuisineModelV2 find(@ApiParam(value = "Cuisine Id", example = "1", required = true) Long cuisineId);

    @ApiOperation("Create cuisine")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Cuisine created"),
    })
    CuisineModelV2 create(@ApiParam(name = "body", value = "Cuisine's representation", required = true) CuisineInputV2 cuisineInput);

    @ApiOperation("Update cuisine by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cuisine Updated"),
        @ApiResponse(code = 404, message = "Cuisine not found", response = Problem.class)
    })
    CuisineModelV2 update(@ApiParam(value = "Cuisine Id", example = "1", required = true) Long cuisineId,
            @ApiParam(name = "body", value = "Representation of new cuisine with with new data", required = true) CuisineInputV2 cuisineInput);

    @ApiOperation("Remove cuisine by Id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cuisine removed"),
        @ApiResponse(code = 404, message = "Cuisine not found", response = Problem.class)
    })
    void delete(@ApiParam(value = "Cuisine Id", example = "1", required = true) Long cuisineid);
}

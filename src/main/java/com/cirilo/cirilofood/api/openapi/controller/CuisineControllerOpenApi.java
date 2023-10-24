package com.cirilo.cirilofood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.CuisineModel;
import com.cirilo.cirilofood.api.model.input.CuisineInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cuisines")
public interface CuisineControllerOpenApi {

    @ApiOperation("List cuisines on pagination")
    PagedModel<CuisineModel> list(Pageable pageable);

    @ApiOperation("Get cuisine by Id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Cuisine Id invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "Cuisine not found", response = Problem.class)
    })
    CuisineModel find(@ApiParam(value = "Cuisine Id", example = "1", required = true) Long cuisineId);

    @ApiOperation("Create cuisine")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Cuisine created"),
    })
    CuisineModel create(@ApiParam(name = "body", value = "Cuisine's representation", required = true) CuisineInput cuisineInput);

    @ApiOperation("Update cuisine by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cuisine Updated"),
        @ApiResponse(code = 404, message = "Cuisine not found", response = Problem.class)
    })
    CuisineModel atualizar(@ApiParam(value = "Cuisine Id", example = "1", required = true) Long cuisineId,
            @ApiParam(name = "body", value = "Representation of new cuisine with with new data", required = true) CuisineInput cuisineInput);

    @ApiOperation("Remove cuisine by Id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cuisine removed"),
        @ApiResponse(code = 404, message = "Cuisine not found", response = Problem.class)
    })
    void remover(@ApiParam(value = "Cuisine Id", example = "1", required = true) Long cuisineid);
}

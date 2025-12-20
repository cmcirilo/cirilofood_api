package com.cirilo.cirilofood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.v1.model.StateModel;
import com.cirilo.cirilofood.api.v1.model.input.StateInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "States")
public interface StateControllerOpenApi {

    @ApiOperation("List states")
    CollectionModel<StateModel> list();

    @ApiOperation("Find state by Id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "State Id invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "State not found", response = Problem.class)
    })
    StateModel find(@ApiParam(value = "State Id", example = "1", required = true) Long stateId);

    @ApiOperation("Create new state")
    @ApiResponses({
        @ApiResponse(code = 201, message = "State created"),
    })
    StateModel create(@ApiParam(name = "body", value = "Representation of new State", required = true) StateInput stateInput);

    @ApiOperation("Update state by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "State updated"),
        @ApiResponse(code = 404, message = "State not found", response = Problem.class)
    })
    StateModel update(@ApiParam(value = "State Id", example = "1", required = true) Long stateId,
            @ApiParam(name = "body", value = "Representation of state with new data", required = true) StateInput stateInput);

    @ApiOperation("Remove state by id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "State removed"),
        @ApiResponse(code = 404, message = "State not found", response = Problem.class)
    })
    void delete(@ApiParam(value = "State Id", example = "1", required = true) Long stateId);

}

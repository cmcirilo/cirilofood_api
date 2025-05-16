package com.cirilo.cirilofood.api.openapi.controller;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(tags = "Orders")
public interface StatusOrderOpenApi {

    @ApiOperation("Order confirmation")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Order confirmed with success"),
        @ApiResponse(code = 404, message = "Order not found", response = Problem.class)
    })
    ResponseEntity<Void> confirm(@ApiParam(value = "Order code", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
            required = true) String code);

    @ApiOperation("Order cancel")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Order canceled with success"),
        @ApiResponse(code = 404, message = "Order not found", response = Problem.class)
    })
    ResponseEntity<Void> cancel(@ApiParam(value = "Order code", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
            required = true) String code);

    @ApiOperation("Order delivery")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Order delivered with success"),
        @ApiResponse(code = 404, message = "Order not found", response = Problem.class)
    })
    ResponseEntity<Void> delivery(@ApiParam(value = "Order code", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
            required = true) String code);

}

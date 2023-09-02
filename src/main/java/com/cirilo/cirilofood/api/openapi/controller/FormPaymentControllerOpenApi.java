package com.cirilo.cirilofood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.FormPaymentModel;
import com.cirilo.cirilofood.api.model.input.FormPaymentInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Forms Payment")
public interface FormPaymentControllerOpenApi {

    @ApiOperation("List Forms Payment")
    ResponseEntity<List<FormPaymentModel>> list(ServletWebRequest request);

    @ApiOperation("Find Form Payment by Id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Form Payment Id invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "Form Payment not found", response = Problem.class)
    })
    ResponseEntity<FormPaymentModel> find(@ApiParam(value = "Form Payment Id", example = "1") Long formPaymentId, ServletWebRequest request);

    @ApiOperation("Create Form Payment")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Form Payment created"),
    })
    FormPaymentModel create(@ApiParam(name = "body", value = "Representation of new Form Payment") FormPaymentInput formPaymentInput);

    @ApiOperation("Update Form Payment by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Form Payment updated"),
        @ApiResponse(code = 404, message = "Form Payment not found", response = Problem.class)
    })
    FormPaymentModel udpate(@ApiParam(value = "Form Payment Id", example = "1") Long formPaymentId,
            @ApiParam(name = "corpo", value = "Representation of new Form Payment with new data") FormPaymentInput formPaymentInput);

    @ApiOperation("Remove Form Payment by Id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Form Payment removed"),
        @ApiResponse(code = 404, message = "Form Payment not found", response = Problem.class)
    })
    void delete(@ApiParam(value = "Form Payment Id", example = "1") Long formPaymentId);

}

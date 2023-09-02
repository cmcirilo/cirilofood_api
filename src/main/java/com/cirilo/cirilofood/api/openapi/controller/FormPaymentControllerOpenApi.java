package com.cirilo.cirilofood.api.openapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.ServletWebRequest;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.FormPaymentModel;
import com.cirilo.cirilofood.api.model.input.FormPaymentInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    ResponseEntity<FormPaymentModel> find(@PathVariable Long formPaymentId, ServletWebRequest request);

    @ApiOperation("Create Form Payment")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Form Payment created"),
    })
    FormPaymentModel create(@RequestBody @Valid FormPaymentInput formPaymentInput);

    @ApiOperation("Update Form Payment by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Form Payment updated"),
        @ApiResponse(code = 404, message = "Form Payment not found", response = Problem.class)
    })
    FormPaymentModel udpate(@PathVariable Long formPaymentId,
            @RequestBody @Valid FormPaymentInput formPaymentInput);

    @ApiOperation("Remove Form Payment by Id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Form Payment removed"),
        @ApiResponse(code = 404, message = "Form Payment not found", response = Problem.class)
    })
    void delete(@PathVariable Long formPaymentId);

}

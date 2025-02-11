package com.cirilo.cirilofood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.OrderModel;
import com.cirilo.cirilofood.api.model.OrderResumeModel;
import com.cirilo.cirilofood.api.model.input.OrderInput;
import com.cirilo.cirilofood.domain.filter.OrderFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Orders")
public interface OrderControllerOpenApi {

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Properties names to filter response separated by comma",
                name = "fields", paramType = "qyery", type = "string")
    })
    @ApiOperation("Searh orders")
    PagedModel<OrderResumeModel> find(OrderFilter orderFilter,
                                      @PageableDefault(size = 10) Pageable pageable);

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Properties names to filter response separated by comma",
                name = "fields", paramType = "query", type = "string")
    })
    @ApiOperation("Searh orders by Id")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Order not found", response = Problem.class)
    })
    OrderModel search(@ApiParam(value = "Order Id", example = "1", required = true) String code);

    @ApiOperation("Create order")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Order created"),
    })
    OrderModel create(@ApiParam(name = "body", value = "Representation of new order", required = true) OrderInput orderInput);

}

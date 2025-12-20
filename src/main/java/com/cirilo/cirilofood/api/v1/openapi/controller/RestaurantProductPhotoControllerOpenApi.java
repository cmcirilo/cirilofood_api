package com.cirilo.cirilofood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.v1.model.ProductPhotoModel;
import com.cirilo.cirilofood.api.v1.model.input.ProductPhotoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "Products")
public interface RestaurantProductPhotoControllerOpenApi {

    @ApiOperation("Update product photo from restaurant")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Product photo updated"),
        @ApiResponse(code = 404, message = "Restanrant's product not found", response = Problem.class)
    })
    ProductPhotoModel updatePhoto(
            @ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(value = "Product Id", example = "1", required = true) Long productId,
            @ApiParam(name = "body", value = "Representation of new product photo", required = true) ProductPhotoInput productPhotoInput,
            @ApiParam(value = "Product photo file (maximum 500KB, only JPG or PNG", hidden = false) MultipartFile file)
        throws IOException;

    @ApiOperation("Find product photo from restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Restanrant Id or product invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "Product photo not found", response = Problem.class)
    })
    ProductPhotoModel find(
            @ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(value = "Product Id", example = "1", required = true) Long productId);

    @ApiOperation(value = "Find product photo from restaurant", hidden = true)
    ResponseEntity<?> download(Long restaurantId, Long productId,
            String acceptHeader)
        throws HttpMediaTypeNotAcceptableException;

    @ApiOperation("Remove product photo from restaurant")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Product photo removed"),
        @ApiResponse(code = 400, message = "Restanrant Id or product invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "Product photo not found", response = Problem.class)
    })
    void remove(@ApiParam(value = "Restaurant Id", example = "1", required = true) Long restaurantId,
            @ApiParam(value = "Product Id", example = "1", required = true) Long productId);
}

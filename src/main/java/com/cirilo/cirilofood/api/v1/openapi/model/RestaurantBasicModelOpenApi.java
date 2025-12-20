package com.cirilo.cirilofood.api.v1.openapi.model;

import java.math.BigDecimal;

import com.cirilo.cirilofood.api.v1.model.CuisineModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RestaurantBasicModel")
@Getter
@Setter
public class RestaurantBasicModelOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    private String name;

    @ApiModelProperty(example = "12.00")
    private BigDecimal shippingFee;

    private CuisineModel cuisine;
}

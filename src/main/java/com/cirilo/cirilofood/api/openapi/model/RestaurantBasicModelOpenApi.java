package com.cirilo.cirilofood.api.openapi.model;

import com.cirilo.cirilofood.api.model.CuisineModel;
import com.cirilo.cirilofood.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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

package com.cirilo.cirilofood.api.model;

import java.math.BigDecimal;

import com.cirilo.cirilofood.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantModel {

    @ApiModelProperty(example = "1")
    @JsonView({RestaurantView.Resume.class, RestaurantView.OnlyName.class})
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    @JsonView({RestaurantView.Resume.class, RestaurantView.OnlyName.class})
    private String name;

    @ApiModelProperty(example = "12.00")
    @JsonView(RestaurantView.Resume.class)
    private BigDecimal shippingFee;

    @JsonView(RestaurantView.Resume.class)
    private CuisineModel cuisine;

    private Boolean active;

    private Boolean opened;

    private AddressModel address;

}

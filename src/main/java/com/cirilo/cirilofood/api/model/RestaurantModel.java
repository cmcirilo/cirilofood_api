package com.cirilo.cirilofood.api.model;

import java.math.BigDecimal;

import com.cirilo.cirilofood.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantModel {

    @JsonView({RestaurantView.Resume.class, RestaurantView.OnlyName.class})
    private Long id;

    @JsonView({RestaurantView.Resume.class, RestaurantView.OnlyName.class})
    private String name;

    @JsonView(RestaurantView.Resume.class)
    private BigDecimal shippingFee;

    @JsonView(RestaurantView.Resume.class)
    private CuisineModel cuisine;

    private Boolean active;

    private Boolean opened;

    private AddressModel address;

}

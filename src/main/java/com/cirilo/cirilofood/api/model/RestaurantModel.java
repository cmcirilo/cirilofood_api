package com.cirilo.cirilofood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantModel {

    private Long id;

    private String name;

    private BigDecimal shippingFee;

    private CuisineModel cuisine;

    private Boolean active;

    private AddressModel address;

}

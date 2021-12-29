package com.cirilo.cirilofood.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantModel {

    private Long id;

    private String name;

    private BigDecimal shippingFee;

    private CuisineModel cuisine;

    private Boolean active;

    private Boolean opened;

    private AddressModel address;

}

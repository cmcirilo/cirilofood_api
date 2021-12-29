package com.cirilo.cirilofood.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantResumeModel {

    private Long id;

    private String name;

    private BigDecimal shippingFee;

    private CuisineModel cuisineModel;

}

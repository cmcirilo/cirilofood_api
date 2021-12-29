package com.cirilo.cirilofood.client.model.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantInput {

    private String name;

    private BigDecimal shippingFee;

    private CuisineIdInput cuisine;

    private AddressInput address;

}

package com.cirilo.cirilofood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResumeModel {

    private String code;

    private BigDecimal subtotal;

    private BigDecimal shippingFee;

    private BigDecimal totalValue;

    private String status;

    private OffsetDateTime createdDate;

    private RestaurantResumeModel restaurant;

    private UserModel client;

}

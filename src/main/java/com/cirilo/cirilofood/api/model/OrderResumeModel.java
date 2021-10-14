package com.cirilo.cirilofood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

@JsonFilter("orderFilter")
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

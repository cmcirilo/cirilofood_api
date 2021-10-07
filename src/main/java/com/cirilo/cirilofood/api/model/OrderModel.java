package com.cirilo.cirilofood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderModel {

    private Long id;

    private BigDecimal subtotal;

    private BigDecimal shippingFee;

    private BigDecimal totalValue;

    private String status;

    private OffsetDateTime createdDate;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime deliveryDate;

    private OffsetDateTime cancelDate;

    private RestaurantResumeModel restaurant;

    private UserModel client;

    private FormPaymentModel formPayment;

    private AddressModel deliveryAddress;

    private List<OrderItemModel> itens;

}

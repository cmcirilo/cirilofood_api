package com.cirilo.cirilofood.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInput {

    @Valid
    @NotNull
    private RestaurantIdInput restaurant;

    @Valid
    @NotNull
    private AddressInput deliveryAddress;

    @Valid
    @NotNull
    private FormPaymentIdInput formPayment;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemInput> itens;
}

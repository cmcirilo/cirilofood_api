package com.cirilo.cirilofood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "orders")
@Setter
@Getter
public class OrderModel extends RepresentationModel<OrderModel> {

    private RestaurantOnlyNameModel restaurantOnlyNameModelt;

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String code;

    @ApiModelProperty(example = "298.90")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "10.00")
    private BigDecimal shippingFee;

    @ApiModelProperty(example = "308.90")
    private BigDecimal totalValue;

    @ApiModelProperty(example = "CRIADO")
    private String status;

    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime createdDate;

    @ApiModelProperty(example = "2019-12-01T20:35:10Z")
    private OffsetDateTime confirmationDate;

    @ApiModelProperty(example = "2019-12-01T20:55:30Z")
    private OffsetDateTime deliveryDate;

    @ApiModelProperty(example = "2019-12-01T20:35:00Z")
    private OffsetDateTime cancelDate;

    private RestaurantResumeModel restaurant;

    private UserModel client;

    private FormPaymentModel formPayment;

    private AddressModel deliveryAddress;

    private List<OrderItemModel> itens;

}

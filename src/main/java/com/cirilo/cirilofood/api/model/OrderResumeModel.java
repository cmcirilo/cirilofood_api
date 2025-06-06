package com.cirilo.cirilofood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

// @JsonFilter("orderFilter")
@Relation(collectionRelation = "orders")
@Setter
@Getter
public class OrderResumeModel extends RepresentationModel<OrderResumeModel> {

    private RestaurantOnlyNameModel restaurantOnlyNameModel;

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String code;

    @ApiModelProperty(example = "298.90")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "10.00")
    private BigDecimal shippingFee;

    @ApiModelProperty(example = "308.90")
    private BigDecimal totalValue;

    @ApiModelProperty(example = "CREATED")
    private String status;

    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime createdDate;

    private RestaurantResumeModel restaurant;

    private UserModel client;

}

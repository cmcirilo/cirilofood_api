package com.cirilo.cirilofood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemModel {

    @ApiModelProperty(example = "1")
    private Long productId;

    @ApiModelProperty(example = "Baby Beef")
    private String productName;

    @ApiModelProperty(example = "2")
    private Integer quantity;

    @ApiModelProperty(example = "78.90")
    private BigDecimal unitPrice;

    @ApiModelProperty(example = "157.80")
    private BigDecimal totalPrice;

    @ApiModelProperty(example = "Less sal, please")
    private String observation;

}

package com.cirilo.cirilofood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Baby Beef")
    private String name;

    @ApiModelProperty(example = "With rice and bean")
    private String description;

    @ApiModelProperty(example = "1")
    private BigDecimal price;

    @ApiModelProperty(example = "1")
    private Boolean active;
}

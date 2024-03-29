package com.cirilo.cirilofood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormPaymentModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Credit Card")
    private String description;

}

package com.cirilo.cirilofood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormPaymentInput {

    @ApiModelProperty(example = "Credit Card", required = true)
    @NotBlank
    private String description;

}

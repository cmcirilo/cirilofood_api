package com.cirilo.cirilofood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuisineInput {

    @ApiModelProperty(example = "Brazilian", required = true)
    @NotBlank
    private String name;
}

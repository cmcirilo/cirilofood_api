package com.cirilo.cirilofood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateInput {

    @ApiModelProperty(example = "Minas Gerais", required = true)
    @NotBlank
    private String name;

}

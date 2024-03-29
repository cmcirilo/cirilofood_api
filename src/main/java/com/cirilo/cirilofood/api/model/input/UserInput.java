package com.cirilo.cirilofood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

    @ApiModelProperty(example = "João da Silva", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "joao.ger@cirilofood.com.br", required = true)
    @NotBlank
    @Email
    private String email;

}

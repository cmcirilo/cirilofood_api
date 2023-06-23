package com.cirilo.cirilofood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityInput {

    @ApiModelProperty(value="City Name", example = "São Paulo")
    @NotBlank
    private String name;

    @Valid
    @NotNull
    private StateIdInput state;

}

package com.cirilo.cirilofood.api.v2.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CityInput")
@Getter
@Setter
public class CityInputV2 {

    @ApiModelProperty(value = "City Name", example = "São Paulo", required = true)
    @NotBlank
    private String nameCity;

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long idState;

}

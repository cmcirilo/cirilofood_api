package com.cirilo.cirilofood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInput {

    @ApiModelProperty(example = "38400-000", required = true)
    @NotBlank
    private String zipCode;

    @ApiModelProperty(example = "Floriano Peixoto Street", required = true)
    @NotBlank
    private String street;

    @ApiModelProperty(example = "1500", required = true)
    @NotBlank
    private String number;

    @ApiModelProperty(example = "901")
    private String complement;

    @ApiModelProperty(example = "Center", required = true)
    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdInput city;
}

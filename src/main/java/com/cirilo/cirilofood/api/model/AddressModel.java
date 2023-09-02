package com.cirilo.cirilofood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    @ApiModelProperty(example = "38400-000")
    private String zipCode;

    @ApiModelProperty(example = "Floriano Peixoto Street")
    private String street;

    @ApiModelProperty(example = "1500")
    private String number;

    @ApiModelProperty(example = "901")
    private String complement;

    @ApiModelProperty(example = "Center")
    private String district;

    private CityResumeModel city;

}

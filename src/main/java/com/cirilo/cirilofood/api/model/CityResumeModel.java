package com.cirilo.cirilofood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResumeModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Uberlândia")
    private String name;

    @ApiModelProperty(example = "Minas Gerais")
    private String state;

}

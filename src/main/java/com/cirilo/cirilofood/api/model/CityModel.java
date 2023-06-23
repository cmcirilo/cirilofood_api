package com.cirilo.cirilofood.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value ="City Model", description = "Represents City Model")
@Getter
@Setter
public class CityModel {

    @ApiModelProperty(value="City Id", example = "1")
    private Long id;

    @ApiModelProperty(value="City Name", example = "São Paulo")
    private String name;

    private StateModel state;

}

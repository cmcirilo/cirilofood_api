package com.cirilo.cirilofood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateModel {

    @ApiModelProperty(value="State Id", example = "1")
    private Long id;

    @ApiModelProperty(value="State Name", example = "São Paulo")
    private String name;

}

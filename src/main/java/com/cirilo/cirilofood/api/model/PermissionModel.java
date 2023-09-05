package com.cirilo.cirilofood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "LIST_CUISINES")
    private String name;

    @ApiModelProperty(example = "Search cuisines allowed")
    private String description;

}

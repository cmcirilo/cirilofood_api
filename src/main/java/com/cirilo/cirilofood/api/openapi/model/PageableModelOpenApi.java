package com.cirilo.cirilofood.api.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Pageable")
@Getter
@Setter
public class PageableModelOpenApi {

    @ApiModelProperty(example = "0", value = "Page number (starts with zero)")
    private int page;

    @ApiModelProperty(example = "10", value = "Elements per page")
    private int size;

    @ApiModelProperty(example = "name,asc", value = "Property name to order")
    private List<String> sort;
}

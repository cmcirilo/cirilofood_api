package com.cirilo.cirilofood.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PageModel")
public class PageModelOpenApi {

    @ApiModelProperty(example = "10", value = "Total records per page")
    private Long size;

    @ApiModelProperty(example = "50", value = "Total elements")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Total pages")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Current page (starts with zero)")
    private Long number;

}

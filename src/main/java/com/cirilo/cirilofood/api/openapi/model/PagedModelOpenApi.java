package com.cirilo.cirilofood.api.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedModelOpenApi<T> {

    private List<T> content;

    @ApiModelProperty(example = "10", value = "Total records per page")
    private Long size;

    @ApiModelProperty(example = "50", value = "Total elements")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Total pages")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Current page (starts with zero)")
    private Long number;
}

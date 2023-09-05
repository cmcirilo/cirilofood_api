package com.cirilo.cirilofood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoModel {

    @ApiModelProperty(value = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String fileName;

    @ApiModelProperty(value = "Baby Beef")
    private String description;

    @ApiModelProperty(value = "image/jpeg")
    private String contentType;

    @ApiModelProperty(value = "202912")
    private Long size;
}

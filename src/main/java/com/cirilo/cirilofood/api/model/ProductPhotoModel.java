package com.cirilo.cirilofood.api.model;

import com.cirilo.cirilofood.domain.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoModel {

    private String fileName;

    private String description;

    private String contentType;

    private Long size;
}

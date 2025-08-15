package com.cirilo.cirilofood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.cirilo.cirilofood.api.model.ProductModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("ProductsModel")
public class ProductsModelOpenApi {

    private ProductsEmbeddedModelOpenApi _embedded;

    private Links _links;

    @Data
    @ApiModel("ProductsEmbeddedModel")
    public class ProductsEmbeddedModelOpenApi {

        private List<ProductModel> products;

    }

}

package com.cirilo.cirilofood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.cirilo.cirilofood.api.model.CuisineModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CuisinesModel")
public class CuisinesModelOpenApi {

    private CuisinesEmbeddedModelOpenApi _embedded;

    private Links _links;

    private PageModelOpenApi page;

    @ApiModel("CuisinesEmbeddedModel")
    @Data
    public class CuisinesEmbeddedModelOpenApi {

        private List<CuisineModel> cuisines;

    }

}

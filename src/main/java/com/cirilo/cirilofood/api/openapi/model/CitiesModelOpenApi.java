package com.cirilo.cirilofood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.cirilo.cirilofood.api.model.CityModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("CitiesModel")
public class CitiesModelOpenApi {

    private CityEmbeddedModelOpenApi _embedded;

    private Links _links;

    @ApiModel("CitiesEmbeddedModel")
    @Data
    public class CityEmbeddedModelOpenApi {

        private List<CityModel> cities;

    }
}

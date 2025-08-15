package com.cirilo.cirilofood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.cirilo.cirilofood.api.model.RestaurantBasicModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("RestaurantsBasicModel")
public class RestaurantsBasicModelOpenApi {

    private RestaurantsEmbeddedModelOpenApi _embedded;

    private Links _links;

    @Data
    @ApiModel("RestaurantsEmbeddedModel")
    public class RestaurantsEmbeddedModelOpenApi {

        private List<RestaurantBasicModel> restaurants;

    }

}

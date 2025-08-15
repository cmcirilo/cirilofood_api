package com.cirilo.cirilofood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.cirilo.cirilofood.api.model.StateModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("StatesModel")
public class StatesModelOpenApi {

    private StatesEmbeddedModelOpenApi _embedded;

    private Links _links;

    @ApiModel("StatesEmbeddedModel")
    @Data
    public class StatesEmbeddedModelOpenApi {

        private List<StateModel> state;

    }
}

package com.cirilo.cirilofood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cuisines")
@Getter
@Setter
public class CuisineModelV2 extends RepresentationModel<CuisineModelV2> {

    @ApiModelProperty(example = "1")
    // @JsonView(RestaurantView.Resume.class)
    private Long idCuisine;

    @ApiModelProperty(example = "Brazilian")
    // @JsonView(RestaurantView.Resume.class)
    private String nameCuisine;

}

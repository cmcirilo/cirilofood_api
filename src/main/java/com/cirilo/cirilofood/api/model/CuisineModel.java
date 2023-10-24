package com.cirilo.cirilofood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.cirilo.cirilofood.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cuisines")
@Getter
@Setter
public class CuisineModel extends RepresentationModel<CuisineModel> {

    @ApiModelProperty(example = "1")
    @JsonView(RestaurantView.Resume.class)
    private Long id;

    @ApiModelProperty(example = "Brazilian")
    @JsonView(RestaurantView.Resume.class)
    private String name;

}

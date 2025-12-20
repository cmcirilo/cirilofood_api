package com.cirilo.cirilofood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "states")
@Getter
@Setter
public class StateModel extends RepresentationModel<StateModel> {

    @ApiModelProperty(value="State Id", example = "1")
    private Long id;

    @ApiModelProperty(value="State Name", example = "São Paulo")
    private String name;

}

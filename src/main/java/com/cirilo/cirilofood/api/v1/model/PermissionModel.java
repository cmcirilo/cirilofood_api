package com.cirilo.cirilofood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissions")
@Setter
@Getter
public class PermissionModel extends RepresentationModel<PermissionModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "LIST_CUISINES")
    private String name;

    @ApiModelProperty(example = "Search cuisines allowed")
    private String description;

}

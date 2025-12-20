package com.cirilo.cirilofood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.cirilo.cirilofood.api.v1.model.GroupModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("GroupsModel")
public class GroupsModelOpenApi {

    private GroupsEmbeddedModelOpenApi _embedded;

    private Links _links;

    @Data
    @ApiModel("GroupsEmbeddedModel")
    public class GroupsEmbeddedModelOpenApi {

        private List<GroupModel> groups;

    }

}

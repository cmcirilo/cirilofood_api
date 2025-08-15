package com.cirilo.cirilofood.api.openapi.model;

import com.cirilo.cirilofood.api.model.PermissionModel;
import org.springframework.hateoas.Links;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("PermissoesModel")
public class PermissionsModelOpenApi {

    private PermissionsEmbeddedModelOpenApi _embedded;

    private Links _links;

    @Data
    @ApiModel(" PermissionsEmbeddedModel")
    public class PermissionsEmbeddedModelOpenApi {

        private List<PermissionModel> permissions;

    }

}

package com.cirilo.cirilofood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.cirilo.cirilofood.api.v1.model.UserModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("UsersModel")
public class UsersModelOpenApi {

    private UsersEmbeddedModelOpenApi _embedded;

    private Links _links;

    @Data
    @ApiModel("UsersEmbeddedModel")
    public class UsersEmbeddedModelOpenApi {

        private List<UserModel> users;

    }

}

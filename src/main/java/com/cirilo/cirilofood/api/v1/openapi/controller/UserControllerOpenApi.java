package com.cirilo.cirilofood.api.v1.openapi.controller;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.v1.model.UserModel;
import com.cirilo.cirilofood.api.v1.model.input.PasswordInput;
import com.cirilo.cirilofood.api.v1.model.input.UserInput;
import com.cirilo.cirilofood.api.v1.model.input.UserWithPasswordInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Users")
public interface UserControllerOpenApi {

    @ApiOperation("Lista os usuários")
    CollectionModel<UserModel> list();

    @ApiOperation("Find user by Id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "User Id invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "User not found", response = Problem.class)
    })
    UserModel find(@ApiParam(value = "User Id", example = "1", required = true) Long userId);

    @ApiOperation("Create user")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Usuário cadastrado"),
    })
    UserModel create(@ApiParam(name = "body", value = "Representation of new user", required = true) UserWithPasswordInput userWithPasswordInput);

    @ApiOperation("Update user by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "User updated"),
        @ApiResponse(code = 404, message = "User not found", response = Problem.class)
    })
    UserModel update(
            @ApiParam(value = "User Id", example = "1", required = true) Long userId,
            @ApiParam(name = "body", value = "Representation of new user", required = true) UserInput userInput);

    @ApiOperation("Update user password")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Password updated with success"),
        @ApiResponse(code = 404, message = "User not found", response = Problem.class)
    })
    void updatePassword(
            @ApiParam(value = "User Id", example = "1", required = true) Long userId,
            @ApiParam(name = "body", value = "Representation of new user", required = true) PasswordInput password);
}

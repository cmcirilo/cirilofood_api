package com.cirilo.cirilofood.api.openapi.controller;

import java.util.List;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.GroupModel;
import com.cirilo.cirilofood.api.model.input.GroupInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Groups")
public interface GroupControllerOpenApi {

    @ApiOperation("List groups")
    List<GroupModel> list();

    @ApiOperation("Find Group by Id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid Group Id", response = Problem.class),
        @ApiResponse(code = 404, message = "Group not found", response = Problem.class)
    })
    GroupModel find(
            @ApiParam(value = "Group Id", example = "1", required = true) Long groupId);

    @ApiOperation("Create Group")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Group added"),
    })
    GroupModel create(
            @ApiParam(name = "body", value = "Representation of new Group", required = true) GroupInput groupId);

    @ApiOperation("Update group by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Group updated"),
        @ApiResponse(code = 404, message = "Group not found", response = Problem.class)
    })
    GroupModel udpate(
            @ApiParam(value = "Group Id", example = "1", required = true) Long grupoId,

            @ApiParam(name = "body", value = "Representation of group with new data", required = true) GroupInput groupId);

    @ApiOperation("Remove group by Id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Group removed"),
        @ApiResponse(code = 404, message = "Group not found", response = Problem.class)
    })
    void delete(
            @ApiParam(value = "Group Id", example = "1", required = true) Long groupId);
}

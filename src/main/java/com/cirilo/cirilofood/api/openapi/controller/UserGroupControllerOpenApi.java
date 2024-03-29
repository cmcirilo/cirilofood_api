package com.cirilo.cirilofood.api.openapi.controller;

import java.util.List;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.GroupModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Users")
public interface UserGroupControllerOpenApi {

    @ApiOperation("List groups associated with users")
    @ApiResponses({
        @ApiResponse(code = 404, message = "User not found", response = Problem.class)
    })
    List<GroupModel> list(@ApiParam(value = "User Id", example = "1", required = true) Long userId);

    @ApiOperation("Disassociate user group")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Disassociation with success"),
        @ApiResponse(code = 404, message = "User or group not found",
                response = Problem.class)
    })
    void disassociate(
            @ApiParam(value = "User Id", example = "1", required = true) Long userId,
            @ApiParam(value = "Group Id", example = "1", required = true) Long groupId);

    @ApiOperation("Associate user group")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Association with success"),
        @ApiResponse(code = 404, message = "User or group not found",
                response = Problem.class)
    })
    void associate(@ApiParam(value = "User Id", example = "1", required = true) Long userId,
            @ApiParam(value = "Group Id", example = "1", required = true) Long groupId);
}

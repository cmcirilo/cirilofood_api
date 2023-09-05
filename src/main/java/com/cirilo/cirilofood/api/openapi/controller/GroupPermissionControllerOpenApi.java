package com.cirilo.cirilofood.api.openapi.controller;

import java.util.List;

import com.cirilo.cirilofood.api.exceptionhandler.Problem;
import com.cirilo.cirilofood.api.model.PermissionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Groups")
public interface GroupPermissionControllerOpenApi {

    @ApiOperation("List permissions associated with a group")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Group Id invalid", response = Problem.class),
        @ApiResponse(code = 404, message = "Group not found", response = Problem.class)
    })
    List<PermissionModel> list(@ApiParam(value = "Group Id", example = "1", required = true) Long groupId);

    @ApiOperation("Disassociation group permission")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Disassociation with success"),
        @ApiResponse(code = 404, message = "Group or permission not found",
                response = Problem.class)
    })
    void desassociate(@ApiParam(value = "Group Id", example = "1", required = true) Long groupId,
            @ApiParam(value = "Permission Id", example = "1", required = true) Long permissionId);

    @ApiOperation("Association group permission")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Association with success"),
        @ApiResponse(code = 404, message = "Group or permission not found",
                response = Problem.class)
    })
    void associate(@ApiParam(value = "Group Id", example = "1", required = true) Long groupId,
            @ApiParam(value = "Permission Id", example = "1", required = true) Long permissionId);
}

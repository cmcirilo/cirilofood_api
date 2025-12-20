package com.cirilo.cirilofood.api.v1.controller;

import com.cirilo.cirilofood.api.v1.assembler.PermissionModelAssembler;
import com.cirilo.cirilofood.api.v1.model.PermissionModel;
import com.cirilo.cirilofood.api.v1.openapi.controller.PermissionControllerOpenApi;
import com.cirilo.cirilofood.domain.model.Permission;
import com.cirilo.cirilofood.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController implements PermissionControllerOpenApi {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionModelAssembler permissionModelAssembler;

    @Override
    @GetMapping
    public CollectionModel<PermissionModel> list() {
        List<Permission> allPermissions = permissionRepository.findAll();

        return permissionModelAssembler.toCollectionModel(allPermissions);
    }
}

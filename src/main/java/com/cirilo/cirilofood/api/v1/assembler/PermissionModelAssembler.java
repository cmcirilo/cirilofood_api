package com.cirilo.cirilofood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.model.PermissionModel;
import com.cirilo.cirilofood.core.security.CiriloSecurity;
import com.cirilo.cirilofood.domain.model.Permission;

@Component
public class PermissionModelAssembler implements RepresentationModelAssembler<Permission, PermissionModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    @Autowired
    private CiriloSecurity ciriloSecurity;

    @Override
    public PermissionModel toModel(Permission permission) {
        return modelMapper.map(permission, PermissionModel.class);
    }

    @Override
    public CollectionModel<PermissionModel> toCollectionModel(Iterable<? extends Permission> entities) {
        CollectionModel<PermissionModel> collectionModel = RepresentationModelAssembler.super.toCollectionModel(entities);

        if (ciriloSecurity.allowListUsersGroupsPermissions()) {
            collectionModel.add(ciriloLinks.linkToPermissions());
        }

        return collectionModel;
    }
}

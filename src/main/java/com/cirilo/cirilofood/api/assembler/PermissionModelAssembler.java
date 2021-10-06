package com.cirilo.cirilofood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.PermissionModel;
import com.cirilo.cirilofood.domain.model.Permission;

@Component
public class PermissionModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissionModel toModel(Permission permission) {
        return modelMapper.map(permission, PermissionModel.class);
    }

    public List<PermissionModel> toCollectionModel(Collection<Permission> permissions) {
        return permissions.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

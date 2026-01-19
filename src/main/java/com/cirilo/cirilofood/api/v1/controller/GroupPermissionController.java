package com.cirilo.cirilofood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.assembler.PermissionModelAssembler;
import com.cirilo.cirilofood.api.v1.model.PermissionModel;
import com.cirilo.cirilofood.api.v1.openapi.controller.GroupPermissionControllerOpenApi;
import com.cirilo.cirilofood.domain.model.Group;
import com.cirilo.cirilofood.domain.service.GroupService;

@RestController
@RequestMapping(path = "/v1/groups/{groupId}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupPermissionController implements GroupPermissionControllerOpenApi {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionModelAssembler permissionModelAssembler;

    @Autowired
    private CiriloLinks ciriloLinks;

    @Override
    @GetMapping
    public CollectionModel<PermissionModel> list(@PathVariable Long groupId) {
        Group group = groupService.find(groupId);

        CollectionModel<PermissionModel> permissions = permissionModelAssembler.toCollectionModel(group.getPermissions())
                .removeLinks()
                .add(ciriloLinks.linkToGroupPermissions(groupId))
                .add(ciriloLinks.linkToGroupPermissionsAssociation(groupId, "associate"));

        permissions.getContent().forEach(permissaoModel -> {
            permissaoModel.add(ciriloLinks.linkToGroupPermissionsDesassociation(
                    groupId, permissaoModel.getId(), "desassociate"));
        });

        return permissions;
    }

    @Override
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.desassociatePermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.associatePermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }
}

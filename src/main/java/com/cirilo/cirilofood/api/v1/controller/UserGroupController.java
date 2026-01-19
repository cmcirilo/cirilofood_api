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
import com.cirilo.cirilofood.api.v1.assembler.GroupModelAssembler;
import com.cirilo.cirilofood.api.v1.model.GroupModel;
import com.cirilo.cirilofood.api.v1.openapi.controller.UserGroupControllerOpenApi;
import com.cirilo.cirilofood.domain.model.User;
import com.cirilo.cirilofood.domain.service.UserService;

@RestController
@RequestMapping(path = "/v1/users/{userId}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGroupController implements UserGroupControllerOpenApi {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @Autowired
    private CiriloLinks ciriloLinks;

    @Override
    @GetMapping
    public CollectionModel<GroupModel> list(@PathVariable Long userId) {
        User user = userService.find(userId);

        CollectionModel<GroupModel> groups = groupModelAssembler.toCollectionModel(user.getGroups())
                .removeLinks()
                .add(ciriloLinks.linkToUserGroupAssociation(userId, "associate"));

        groups.getContent().forEach(groupModel -> {
            groupModel.add(ciriloLinks.linkToUserGroupDesassociation(
                    userId, groupModel.getId(), "desassociate"));
        });

        return groups;
    }

    @Override
    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.dsassociateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.associateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }
}

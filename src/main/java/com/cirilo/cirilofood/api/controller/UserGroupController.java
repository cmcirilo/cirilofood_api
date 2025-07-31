package com.cirilo.cirilofood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.assembler.GroupModelAssembler;
import com.cirilo.cirilofood.api.model.GroupModel;
import com.cirilo.cirilofood.api.openapi.controller.UserGroupControllerOpenApi;
import com.cirilo.cirilofood.domain.model.User;
import com.cirilo.cirilofood.domain.service.UserService;

@RestController
@RequestMapping(path = "/users/{userId}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGroupController implements UserGroupControllerOpenApi {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @Override
    @GetMapping
    public CollectionModel<GroupModel> list(@PathVariable Long userId) {
        User user = userService.find(userId);

        return groupModelAssembler.toCollectioModel(user.getGroups())
                .removeLinks();
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.dsassociateGroup(userId, groupId);
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.associateGroup(userId, groupId);
    }
}

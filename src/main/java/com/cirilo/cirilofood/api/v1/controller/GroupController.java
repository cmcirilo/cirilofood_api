package com.cirilo.cirilofood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.v1.assembler.GroupInputDisassembler;
import com.cirilo.cirilofood.api.v1.assembler.GroupModelAssembler;
import com.cirilo.cirilofood.api.v1.model.GroupModel;
import com.cirilo.cirilofood.api.v1.model.input.GroupInput;
import com.cirilo.cirilofood.api.v1.openapi.controller.GroupControllerOpenApi;
import com.cirilo.cirilofood.domain.model.Group;
import com.cirilo.cirilofood.domain.repository.GroupRepository;
import com.cirilo.cirilofood.domain.service.GroupService;

@RestController
@RequestMapping(path = "/v1/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController implements GroupControllerOpenApi {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @Autowired
    private GroupInputDisassembler groupInputDisassembler;

    @Override
    @GetMapping
    public CollectionModel<GroupModel> list() {
        List<Group> groups = groupRepository.findAll();
        return groupModelAssembler.toCollectioModel(groups);
    }

    @GetMapping("/{groupId}")
    public GroupModel find(@PathVariable Long groupId) {
        Group group = groupService.find(groupId);
        return groupModelAssembler.toModel(group);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupModel create(@RequestBody @Valid GroupInput groupInput) {
        Group group = groupInputDisassembler.toDomainObject(groupInput);

        group = groupService.save(group);

        return groupModelAssembler.toModel(group);
    }

    @PutMapping("/{groupId}")
    public GroupModel udpate(@PathVariable Long groupId,
            @RequestBody @Valid GroupInput groupInput) {
        Group currentGroup = groupService.find(groupId);

        groupInputDisassembler.copyToDomainObject(groupInput, currentGroup);

        currentGroup = groupService.save(currentGroup);

        return groupModelAssembler.toModel(currentGroup);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long groupId) {
        groupService.delete(groupId);
    }

}

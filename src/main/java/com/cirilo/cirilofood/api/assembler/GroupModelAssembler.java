package com.cirilo.cirilofood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.CiriloLinks;
import com.cirilo.cirilofood.api.controller.GroupController;
import com.cirilo.cirilofood.api.model.GroupModel;
import com.cirilo.cirilofood.domain.model.Group;

@Component
public class GroupModelAssembler extends RepresentationModelAssemblerSupport<Group, GroupModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public GroupModelAssembler() {
        super(GroupController.class, GroupModel.class);
    }

    @Override
    public GroupModel toModel(Group group) {
        GroupModel groupModel = createModelWithId(group.getId(), group);
        modelMapper.map(group, groupModel);

        groupModel.add(ciriloLinks.linkToGroups("groups"));

        groupModel.add(ciriloLinks.linkToGroupPermissions(group.getId(), "permissions"));

        return groupModel;
    }

    // @Override
    public CollectionModel<GroupModel> toCollectioModel(Iterable<? extends Group> entities) {
        return super.toCollectionModel(entities)
                .add(ciriloLinks.linkToGroups());
    }

}

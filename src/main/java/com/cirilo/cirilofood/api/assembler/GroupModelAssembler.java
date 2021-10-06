package com.cirilo.cirilofood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.GroupModel;
import com.cirilo.cirilofood.domain.model.Group;

@Component
public class GroupModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GroupModel toModel(Group group) {
        return modelMapper.map(group, GroupModel.class);
    }

    public List<GroupModel> toCollectioModel(Collection<Group> groups) {
        return groups.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}

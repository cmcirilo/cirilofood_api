package com.cirilo.cirilofood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.controller.UserController;
import com.cirilo.cirilofood.api.v1.model.UserModel;
import com.cirilo.cirilofood.core.security.CiriloSecurity;
import com.cirilo.cirilofood.domain.model.User;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    @Autowired
    private CiriloSecurity ciriloSecurity;

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    public UserModel toModel(User user) {
        UserModel userModel = createModelWithId(user.getId(), user);
        modelMapper.map(user, userModel);

        if (ciriloSecurity.allowListUsersGroupsPermissions()) {
            userModel.add(ciriloLinks.linkToUsers("users"));
            userModel.add(ciriloLinks.linkToGroupsUser(user.getId(), "user-groups"));
        }

        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
        return super.toCollectionModel(entities)
                .add(ciriloLinks.linkToUsers());
    }

}

package com.cirilo.cirilofood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.controller.UserController;
import com.cirilo.cirilofood.api.controller.UserGroupController;
import com.cirilo.cirilofood.api.model.UserModel;
import com.cirilo.cirilofood.domain.model.User;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    @Autowired
    private ModelMapper modelMapper;

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    public UserModel toModel(User user) {
        UserModel userModel = createModelWithId(user.getId(), user);
        modelMapper.map(user, userModel);

        userModel.add(linkTo(UserController.class).withRel("users"));

        userModel.add(linkTo(methodOn(UserGroupController.class)
                .list(user.getId())).withRel("user-groups"));

        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(UserController.class).withSelfRel());
    }

}

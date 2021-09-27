package com.cirilo.cirilofood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.assembler.UserInputDisassembler;
import com.cirilo.cirilofood.api.assembler.UserModelAssembler;
import com.cirilo.cirilofood.api.model.UserModel;
import com.cirilo.cirilofood.api.model.input.PasswordInput;
import com.cirilo.cirilofood.api.model.input.UserInput;
import com.cirilo.cirilofood.api.model.input.UserWithPasswordInput;
import com.cirilo.cirilofood.domain.model.User;
import com.cirilo.cirilofood.domain.repository.UserRepository;
import com.cirilo.cirilofood.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private UserInputDisassembler userInputDisassembler;

    @GetMapping
    public List<UserModel> list() {
        List<User> allUsers = userRepository.findAll();

        return userModelAssembler.toCollectionModel(allUsers);
    }

    @GetMapping("/{userId}")
    public UserModel find(@PathVariable Long userId) {
        User user = userService.find(userId);

        return userModelAssembler.toModel(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel create(@RequestBody @Valid UserWithPasswordInput userWithPasswordInput) {
        User user = userInputDisassembler.toDomainObject(userWithPasswordInput);
        user = userService.save(user);

        return userModelAssembler.toModel(user);
    }

    @PutMapping("/{userId}")
    public UserModel update(@PathVariable Long userId,
                            @RequestBody @Valid UserInput userInput) {
        User currentUser = userService.find(userId);
        userInputDisassembler.copyToDomainObject(userInput, currentUser);
        currentUser = userService.save(currentUser);

        return userModelAssembler.toModel(currentUser);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long userId, @RequestBody @Valid PasswordInput password) {
        userService.updatePassword(userId, password.getCurrentPassword(), password.getNewPassword());
    }

}

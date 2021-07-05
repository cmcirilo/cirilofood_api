package com.cirilo.cirilofood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.cirilo.cirilofood.domain.model.State;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.domain.repository.StateRepository;
import com.cirilo.cirilofood.domain.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> list() {
        return stateRepository.findAll();
    }

    @GetMapping("/{stateId}")
    public State find(@PathVariable Long stateId) {
        return stateService.find(stateId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State create(@RequestBody @Valid State state) {
        return stateService.save(state);
    }

    @PutMapping("/{stateId}")
    public State update(@PathVariable Long stateId,
                        @RequestBody @Valid State state) {

        State currenteState = stateService.find(stateId);
        BeanUtils.copyProperties(state, currenteState, "id");
        return stateService.save(currenteState);
    }

    @DeleteMapping("/{stateId}")
    public void delete(@PathVariable Long stateId) {
        stateService.delete(stateId);
    }

}

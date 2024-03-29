package com.cirilo.cirilofood.api.controller;

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

import com.cirilo.cirilofood.api.assembler.StateInputDisassembler;
import com.cirilo.cirilofood.api.assembler.StateModelAssembler;
import com.cirilo.cirilofood.api.model.StateModel;
import com.cirilo.cirilofood.api.model.input.StateInput;
import com.cirilo.cirilofood.api.openapi.controller.StateControllerOpenApi;
import com.cirilo.cirilofood.domain.model.State;
import com.cirilo.cirilofood.domain.repository.StateRepository;
import com.cirilo.cirilofood.domain.service.StateService;

@RestController
@RequestMapping(path = "/states", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController implements StateControllerOpenApi {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @Autowired
    private StateModelAssembler stateModelAssembler;

    @Autowired
    private StateInputDisassembler stateInputDisassembler;

    @GetMapping
    public CollectionModel<StateModel> list() {
        List<State> states = stateRepository.findAll();
        return stateModelAssembler.toCollectionModel(states);
    }

    @GetMapping("/{stateId}")
    public StateModel find(@PathVariable Long stateId) {
        State state = stateService.find(stateId);
        return stateModelAssembler.toModel(state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateModel create(@RequestBody @Valid StateInput stateInput) {
        State state = stateInputDisassembler.toDomainObject(stateInput);
        state = stateService.save(state);

        return stateModelAssembler.toModel(state);
    }

    @PutMapping("/{stateId}")
    public StateModel update(@PathVariable Long stateId,
            @RequestBody @Valid StateInput stateInput) {

        State currenteState = stateService.find(stateId);
        stateInputDisassembler.copyToDomainObject(stateInput, currenteState);
        currenteState = stateService.save(currenteState);

        return stateModelAssembler.toModel(currenteState);
    }

    @DeleteMapping("/{stateId}")
    public void delete(@PathVariable Long stateId) {
        stateService.delete(stateId);
    }

}

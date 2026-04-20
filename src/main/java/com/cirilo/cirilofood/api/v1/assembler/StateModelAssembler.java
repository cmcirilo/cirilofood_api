package com.cirilo.cirilofood.api.v1.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.controller.StateController;
import com.cirilo.cirilofood.api.v1.model.StateModel;
import com.cirilo.cirilofood.core.security.CiriloSecurity;
import com.cirilo.cirilofood.domain.model.State;

@Component
public class StateModelAssembler extends RepresentationModelAssemblerSupport<State, StateModel> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    @Autowired
    private CiriloSecurity ciriloSecurity;

    public StateModelAssembler() {
        super(StateController.class, StateModel.class);
    }

    @Override
    public StateModel toModel(State state) {
        StateModel stateModel = createModelWithId(state.getId(), state);
        modelMapper.map(state, stateModel);

        stateModel.add(linkTo(StateController.class).withRel("states"));

        if (ciriloSecurity.allowListStates()) {
            stateModel.add(ciriloLinks.linkToStates("states"));
        }

        return stateModel;
    }

    @Override
    public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities) {
        CollectionModel<StateModel> collectionModel = super.toCollectionModel(entities);

        if (ciriloSecurity.allowListStates()) {
            collectionModel.add(ciriloLinks.linkToStates());
        }

        return collectionModel;
    }
}

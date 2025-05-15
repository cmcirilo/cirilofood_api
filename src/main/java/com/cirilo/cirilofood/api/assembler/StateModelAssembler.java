package com.cirilo.cirilofood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.cirilo.cirilofood.api.CiriloLinks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.controller.StateController;
import com.cirilo.cirilofood.api.model.StateModel;
import com.cirilo.cirilofood.domain.model.State;

@Component
public class StateModelAssembler extends RepresentationModelAssemblerSupport<State, StateModel> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public StateModelAssembler() {
        super(StateController.class, StateModel.class);
    }

    @Override
    public StateModel toModel(State state) {
        StateModel stateModel = createModelWithId(state.getId(), state);
        modelMapper.map(state, stateModel);

        stateModel.add(linkTo(StateController.class).withRel("states"));
        stateModel.add(ciriloLinks.linkToStates("states"));

        return stateModel;
    }

    @Override
    public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(StateController.class).withSelfRel());
    }
}

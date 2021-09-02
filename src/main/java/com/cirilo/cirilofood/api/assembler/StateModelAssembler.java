package com.cirilo.cirilofood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.cirilo.cirilofood.api.model.StateModel;
import com.cirilo.cirilofood.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.RestaurantModel;
import com.cirilo.cirilofood.domain.model.Restaurant;

@Component
public class StateModelAssembler { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    public StateModel toModel(State state) {
        return modelMapper.map(state, StateModel.class);
    }

    public List<StateModel> toCollectionModel(List<State> states) {
        return states.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

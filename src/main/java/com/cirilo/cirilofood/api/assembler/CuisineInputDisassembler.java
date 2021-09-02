package com.cirilo.cirilofood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.input.CuisineInput;
import com.cirilo.cirilofood.domain.model.Cuisine;

@Component
public class CuisineInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cuisine toDomainObject(CuisineInput cuisineInput) {
        return modelMapper.map(cuisineInput, Cuisine.class);
    }

    public void copyToDomainObject(CuisineInput cuisineInput, Cuisine city) {
        modelMapper.map(cuisineInput, city);
    }
}

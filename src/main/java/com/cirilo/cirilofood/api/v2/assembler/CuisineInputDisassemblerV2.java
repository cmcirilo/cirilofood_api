package com.cirilo.cirilofood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v2.model.input.CuisineInputV2;
import com.cirilo.cirilofood.domain.model.Cuisine;

@Component
public class CuisineInputDisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public Cuisine toDomainObject(CuisineInputV2 cuisineInput) {
        return modelMapper.map(cuisineInput, Cuisine.class);
    }

    public void copyToDomainObject(CuisineInputV2 cuisineInput, Cuisine city) {
        modelMapper.map(cuisineInput, city);
    }
}

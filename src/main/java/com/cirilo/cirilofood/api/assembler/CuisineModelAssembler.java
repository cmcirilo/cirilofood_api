package com.cirilo.cirilofood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.cirilo.cirilofood.api.model.CuisineModel;
import com.cirilo.cirilofood.domain.model.Cuisine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.CityModel;
import com.cirilo.cirilofood.domain.model.City;

@Component
public class CuisineModelAssembler { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    public CuisineModel toModel(Cuisine cuisine) {
        return modelMapper.map(cuisine, CuisineModel.class);
    }

    public List<CuisineModel> toCollectionModel(List<Cuisine> cuisines) {
        return cuisines.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

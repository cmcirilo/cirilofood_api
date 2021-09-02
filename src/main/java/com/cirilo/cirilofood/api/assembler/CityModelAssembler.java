package com.cirilo.cirilofood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.CityModel;
import com.cirilo.cirilofood.domain.model.City;

@Component
public class CityModelAssembler { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    public CityModel toModel(City city) {
        return modelMapper.map(city, CityModel.class);
    }

    public List<CityModel> toCollectionModel(List<City> cities) {
        return cities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

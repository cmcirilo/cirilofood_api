package com.cirilo.cirilofood.api.v2.assembler;

import com.cirilo.cirilofood.api.v2.model.input.CityInputV2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.model.input.CityInput;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.model.State;

@Component
public class CityInputDisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public City toDomainObject(CityInputV2 cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInputV2 cityInput, City city) {
        city.setState(new State());
        modelMapper.map(cityInput, city);
    }
}

package com.cirilo.cirilofood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.controller.CityController;
import com.cirilo.cirilofood.api.controller.StateController;
import com.cirilo.cirilofood.api.model.CityModel;
import com.cirilo.cirilofood.domain.model.City;

@Component
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityModel> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    public CityModelAssembler() {
        super(CityController.class, CityModel.class);
    }

    @Override
    public CityModel toModel(City city) {
        CityModel cityModel = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityModel);

        cityModel.add(linkTo(methodOn(CityController.class).list()).withRel("cities"));
        cityModel.getState().add(linkTo(methodOn(StateController.class).find(cityModel.getState().getId())).withSelfRel());

        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities).add(linkTo(CityController.class).withSelfRel());
    }
}

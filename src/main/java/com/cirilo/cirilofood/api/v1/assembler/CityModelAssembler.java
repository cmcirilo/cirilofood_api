package com.cirilo.cirilofood.api.v1.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.controller.CityController;
import com.cirilo.cirilofood.api.v1.model.CityModel;
import com.cirilo.cirilofood.domain.model.City;

@Component
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityModel> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public CityModelAssembler() {
        super(CityController.class, CityModel.class);
    }

    @Override
    public CityModel toModel(City city) {
        CityModel cityModel = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityModel);

        cityModel.add(ciriloLinks.linkToCities("cities"));
        cityModel.getState().add(ciriloLinks.linkToState(cityModel.getState().getId()));

        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                .add(ciriloLinks.linkToCities());
    }
}

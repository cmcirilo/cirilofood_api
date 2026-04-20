package com.cirilo.cirilofood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.controller.CityController;
import com.cirilo.cirilofood.api.v1.model.CityModel;
import com.cirilo.cirilofood.core.security.CiriloSecurity;
import com.cirilo.cirilofood.domain.model.City;

@Component
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityModel> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    @Autowired
    private CiriloSecurity ciriloSecurity;

    public CityModelAssembler() {
        super(CityController.class, CityModel.class);
    }

    @Override
    public CityModel toModel(City city) {
        CityModel cityModel = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityModel);

        if (ciriloSecurity.allowListCities()) {
            cityModel.add(ciriloLinks.linkToCities("cities"));
        }

        if (ciriloSecurity.allowListStates()) {
            cityModel.getState().add(ciriloLinks.linkToState(cityModel.getState().getId()));
        }

        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        CollectionModel<CityModel> collectionModel = super.toCollectionModel(entities);

        if (ciriloSecurity.allowListCities()) {
            collectionModel.add(ciriloLinks.linkToCities());
        }

        return collectionModel;
    }
}

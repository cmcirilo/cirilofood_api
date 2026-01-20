package com.cirilo.cirilofood.api.v2.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v2.CiriloLinksV2;
import com.cirilo.cirilofood.api.v2.controller.CuisineControllerV2;
import com.cirilo.cirilofood.api.v2.model.CuisineModelV2;
import com.cirilo.cirilofood.domain.model.Cuisine;

@Component
public class CuisineModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cuisine, CuisineModelV2> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinksV2 ciriloLinks;

    public CuisineModelAssemblerV2() {
        super(CuisineControllerV2.class, CuisineModelV2.class);
    }

    @Override
    public CuisineModelV2 toModel(Cuisine cuisine) {
        CuisineModelV2 cuisineModel = createModelWithId(cuisine.getId(), cuisine);
        modelMapper.map(cuisine, cuisineModel);

        cuisineModel.add(ciriloLinks.linkToCuisines("cuisines"));

        return cuisineModel;
    }

    public List<CuisineModelV2> toCollectionModel(List<Cuisine> cuisines) {
        return cuisines.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

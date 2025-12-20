package com.cirilo.cirilofood.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.controller.CuisineController;
import com.cirilo.cirilofood.api.v1.model.CuisineModel;
import com.cirilo.cirilofood.domain.model.Cuisine;

@Component
public class CuisineModelAssembler extends RepresentationModelAssemblerSupport<Cuisine, CuisineModel> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public CuisineModelAssembler() {
        super(CuisineController.class, CuisineModel.class);
    }

    @Override
    public CuisineModel toModel(Cuisine cuisine) {
        CuisineModel cuisineModel = createModelWithId(cuisine.getId(), cuisine);
        modelMapper.map(cuisine, cuisineModel);

        cuisineModel.add(ciriloLinks.linkToCuisines("cuisines"));

        return cuisineModel;
    }

    public List<CuisineModel> toCollectionModel(List<Cuisine> cuisines) {
        return cuisines.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

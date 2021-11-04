package com.cirilo.cirilofood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.cirilo.cirilofood.api.model.ProductPhotoModel;
import com.cirilo.cirilofood.domain.model.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.StateModel;
import com.cirilo.cirilofood.domain.model.State;

@Component
public class ProductPhotoModelAssembler { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    public ProductPhotoModel toModel(ProductPhoto productPhoto) {
        return modelMapper.map(productPhoto, ProductPhotoModel.class);
    }

}

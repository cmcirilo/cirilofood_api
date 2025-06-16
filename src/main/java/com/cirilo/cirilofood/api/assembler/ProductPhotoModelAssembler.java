package com.cirilo.cirilofood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.CiriloLinks;
import com.cirilo.cirilofood.api.controller.RestaurantProductPhotoController;
import com.cirilo.cirilofood.api.model.ProductPhotoModel;
import com.cirilo.cirilofood.domain.model.ProductPhoto;

@Component
public class ProductPhotoModelAssembler extends RepresentationModelAssemblerSupport<ProductPhoto, ProductPhotoModel> { // class to convert entity to DTO

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public ProductPhotoModelAssembler() {
        super(RestaurantProductPhotoController.class, ProductPhotoModel.class);
    }

    @Override
    public ProductPhotoModel toModel(ProductPhoto productPhoto) {
        ProductPhotoModel productPhotoModel = modelMapper.map(productPhoto, ProductPhotoModel.class);

        productPhotoModel.add(ciriloLinks.linkToProductPhoto(
                productPhoto.getRestaurantId(), productPhoto.getProduct().getId()));

        productPhotoModel.add(ciriloLinks.linkToProduct(
                productPhoto.getRestaurantId(), productPhoto.getProduct().getId(), "product"));

        return productPhotoModel;
    }

}

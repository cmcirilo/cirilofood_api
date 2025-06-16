package com.cirilo.cirilofood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.CiriloLinks;
import com.cirilo.cirilofood.api.controller.RestaurantProductController;
import com.cirilo.cirilofood.api.model.ProductModel;
import com.cirilo.cirilofood.domain.model.Product;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public ProductModelAssembler() {
        super(RestaurantProductController.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(Product product) {
        ProductModel productModel = createModelWithId(
                product.getId(), product, product.getRestaurant().getId());

        modelMapper.map(product, productModel);

        productModel.add(ciriloLinks.linkToProducts(product.getRestaurant().getId(), "products"));

        productModel.add(ciriloLinks.linkToProductPhoto(
                product.getRestaurant().getId(), product.getId(), "photo"));

        return productModel;
    }

}

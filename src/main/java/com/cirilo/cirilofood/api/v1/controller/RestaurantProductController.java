package com.cirilo.cirilofood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.assembler.ProductInputDisassembler;
import com.cirilo.cirilofood.api.v1.assembler.ProductModelAssembler;
import com.cirilo.cirilofood.api.v1.model.ProductModel;
import com.cirilo.cirilofood.api.v1.model.input.ProductInput;
import com.cirilo.cirilofood.api.v1.openapi.controller.RestaurantProductControllerOpenApi;
import com.cirilo.cirilofood.domain.model.Product;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.repository.ProductRepository;
import com.cirilo.cirilofood.domain.service.ProductService;
import com.cirilo.cirilofood.domain.service.RestaurantService;

@RestController
@RequestMapping(path = "/restaurants/{restaurantId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProductController implements RestaurantProductControllerOpenApi {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductModelAssembler productModelAssembler;

    @Autowired
    private ProductInputDisassembler productInputDisassembler;

    @Autowired
    private CiriloLinks ciriloLinks;

    @Override
    @GetMapping
    public CollectionModel<ProductModel> list(@PathVariable Long restaurantId, @RequestParam(required = false) Boolean includeInactives) {
        Restaurant restaurant = restaurantService.find(restaurantId);
        List<Product> allProducts;

        if (includeInactives != null && includeInactives) {
            allProducts = productRepository.findAllByRestaurant(restaurant);
        } else {
            allProducts = productRepository.findActivesByRestaurant(restaurant);
        }

        return productModelAssembler.toCollectionModel(allProducts)
                .add(ciriloLinks.linkToProducts(restaurantId));
    }

    @Override
    @GetMapping("/{productId}")
    public ProductModel find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productService.find(restaurantId, productId);

        return productModelAssembler.toModel(product);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel create(@PathVariable Long restaurantId,
            @RequestBody @Valid ProductInput productInput) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        Product product = productInputDisassembler.toDomainObject(productInput);
        product.setRestaurant(restaurant);

        product = productService.save(product);

        return productModelAssembler.toModel(product);
    }

    @Override
    @PutMapping("/{productId}")
    public ProductModel update(@PathVariable Long restaurantId, @PathVariable Long productId,
            @RequestBody @Valid ProductInput productInput) {
        Product currentProduct = productService.find(restaurantId, productId);

        productInputDisassembler.copyToDomainObject(productInput, currentProduct);

        currentProduct = productService.save(currentProduct);

        return productModelAssembler.toModel(currentProduct);
    }
}

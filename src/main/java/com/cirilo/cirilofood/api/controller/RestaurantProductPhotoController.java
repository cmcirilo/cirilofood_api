package com.cirilo.cirilofood.api.controller;

import javax.validation.Valid;

import com.cirilo.cirilofood.api.assembler.ProductPhotoModelAssembler;
import com.cirilo.cirilofood.api.model.ProductPhotoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cirilo.cirilofood.api.model.input.ProductPhotoInput;
import com.cirilo.cirilofood.domain.model.Product;
import com.cirilo.cirilofood.domain.model.ProductPhoto;
import com.cirilo.cirilofood.domain.service.ProductPhotoService;
import com.cirilo.cirilofood.domain.service.ProductService;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private ProductPhotoService productPhotoService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPhotoModelAssembler productPhotoModelAssembler;

    // @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public void updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
    // @Valid ProductPhotoInput productPhotoInput) {
    //
    // var fileName = UUID.randomUUID()
    // + "_" + productPhotoInput.getFile().getOriginalFilename();
    //
    // var filePhoto = Path.of("/home/cirilos/Documents/Projects", fileName);
    //
    // try {
    // productPhotoInput.getFile().transferTo(filePhoto);
    // } catch (IOException e) {
    // throw new RuntimeException(e);
    // }
    //
    // }

     @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoModel updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                                         @Valid ProductPhotoInput productPhotoInput) {

        Product product = productService.find(restaurantId, productId);

        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setProduct(product);
        productPhoto.setDescription(productPhotoInput.getDescription());
        productPhoto.setContentType(file.getContentType());
        productPhoto.setSize(file.getSize());
        productPhoto.setFileName(file.getOriginalFilename());

        ProductPhoto productPhotoSaved = productPhotoService.save(productPhoto);

        return productPhotoModelAssembler.toModel(productPhotoSaved);
    }
}

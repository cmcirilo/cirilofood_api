package com.cirilo.cirilofood.api.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.print.DocFlavor;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;

import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cirilo.cirilofood.api.assembler.ProductPhotoModelAssembler;
import com.cirilo.cirilofood.api.model.ProductPhotoModel;
import com.cirilo.cirilofood.api.model.input.ProductPhotoInput;
import com.cirilo.cirilofood.domain.model.Product;
import com.cirilo.cirilofood.domain.model.ProductPhoto;
import com.cirilo.cirilofood.domain.service.PhotoStorageService;
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

    @Autowired
    private PhotoStorageService photoStorageService;

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
            @Valid ProductPhotoInput productPhotoInput)
        throws IOException {

        Product product = productService.find(restaurantId, productId);

        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setProduct(product);
        productPhoto.setDescription(productPhotoInput.getDescription());
        productPhoto.setContentType(file.getContentType());
        productPhoto.setSize(file.getSize());
        productPhoto.setFileName(file.getOriginalFilename());

        ProductPhoto productPhotoSaved = productPhotoService.save(productPhoto, file.getInputStream());

        return productPhotoModelAssembler.toModel(productPhotoSaved);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoModel find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        ProductPhoto productPhoto = productPhotoService.find(restaurantId, productId);

        return productPhotoModelAssembler.toModel(productPhoto);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> download(@PathVariable Long restaurantId, @PathVariable Long productId) {
        try{
            ProductPhoto productPhoto = productPhotoService.find(restaurantId, productId);

            InputStream inputStream = photoStorageService.find(productPhoto.getFileName());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }



    }

}

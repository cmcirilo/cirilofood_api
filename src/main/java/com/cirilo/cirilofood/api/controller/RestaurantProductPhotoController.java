package com.cirilo.cirilofood.api.controller;

import com.cirilo.cirilofood.api.assembler.ProductPhotoModelAssembler;
import com.cirilo.cirilofood.api.model.ProductPhotoModel;
import com.cirilo.cirilofood.api.model.input.ProductPhotoInput;
import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import com.cirilo.cirilofood.domain.model.Product;
import com.cirilo.cirilofood.domain.model.ProductPhoto;
import com.cirilo.cirilofood.domain.service.PhotoStorageService;
import com.cirilo.cirilofood.domain.service.PhotoStorageService.RecoveredPhoto;
import com.cirilo.cirilofood.domain.service.ProductPhotoService;
import com.cirilo.cirilofood.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<?> download(@PathVariable Long restaurantId, @PathVariable Long productId,
            @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {

        try {
            ProductPhoto productPhoto = productPhotoService.find(restaurantId, productId);

            MediaType mediaTypePhoto = MediaType.parseMediaType(productPhoto.getContentType());
            List<MediaType> acceptMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            verifyCompatibilityMediaType(mediaTypePhoto, acceptMediaTypes);

            RecoveredPhoto recoveredPhoto = photoStorageService.find(productPhoto.getFileName());

            if (recoveredPhoto.hasUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, recoveredPhoto.getUrl())
                        .build();
            }

            return ResponseEntity.ok()
                    .contentType(mediaTypePhoto)
                    .body(new InputStreamResource(recoveredPhoto.getInputStream()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long restaurantId,
            @PathVariable Long productId) {
        productPhotoService.remove(restaurantId, productId);
    }

    private void verifyCompatibilityMediaType(MediaType mediaTypePhoto, List<MediaType> acceptMediaTypes)
            throws HttpMediaTypeNotAcceptableException {

        boolean compatible = acceptMediaTypes.stream()
                .anyMatch(acceptMediaType -> acceptMediaType.isCompatibleWith(mediaTypePhoto));

        if (!compatible) {
            throw new HttpMediaTypeNotAcceptableException(acceptMediaTypes);
        }
    }

}

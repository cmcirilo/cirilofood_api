package com.cirilo.cirilofood.api.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.model.input.ProductPhotoInput;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
            @Valid ProductPhotoInput productPhotoInput) {

        var fileName = UUID.randomUUID()
                + "_" + productPhotoInput.getFile().getOriginalFilename();

        var filePhoto = Path.of("/home/cirilos/Documents/Projects", fileName);

        try {
            productPhotoInput.getFile().transferTo(filePhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

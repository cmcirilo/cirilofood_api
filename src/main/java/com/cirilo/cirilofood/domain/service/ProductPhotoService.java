package com.cirilo.cirilofood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cirilo.cirilofood.domain.model.ProductPhoto;
import com.cirilo.cirilofood.domain.repository.ProductRepository;
import com.cirilo.cirilofood.domain.service.PhotoStorageService.Photo;

@Service
public class ProductPhotoService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto, InputStream fileData) {
        Long restaurantId = productPhoto.getRestaurantId();
        Long productId = productPhoto.getProduct().getId();
        String newFileName = photoStorageService.generateFileName(productPhoto.getFileName());

        Optional<ProductPhoto> currentProductPhoto = productRepository.findProductPhotoById(restaurantId, productId);

        if (currentProductPhoto.isPresent()) {
            productRepository.delete(currentProductPhoto.get());
        }

        productPhoto.setFileName(newFileName);
        productPhoto = productRepository.save(productPhoto);
        productRepository.flush();

        Photo newPhoto = Photo.builder()
                .fileName(productPhoto.getFileName())
                .inputStream(fileData)
                .build();

        photoStorageService.upload(newPhoto);

        return productPhoto;
    }

}

package com.cirilo.cirilofood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import com.cirilo.cirilofood.domain.exception.ProductPhotoNotFoundException;
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
        String existingFile = null;

        Optional<ProductPhoto> currentProductPhoto = productRepository.findProductPhotoById(restaurantId, productId);

        if (currentProductPhoto.isPresent()) {
            existingFile = currentProductPhoto.get().getFileName();
            productRepository.delete(currentProductPhoto.get());
        }

        productPhoto.setFileName(newFileName);
        productPhoto = productRepository.save(productPhoto);
        productRepository.flush();

        Photo newPhoto = Photo.builder()
                .fileName(productPhoto.getFileName())
                .inputStream(fileData)
                .build();

        photoStorageService.replace(existingFile, newPhoto);

        return productPhoto;
    }

    public ProductPhoto find(Long restaurantId, Long productId){
        return productRepository.findProductPhotoById(restaurantId, productId)
                .orElseThrow(() -> new ProductPhotoNotFoundException(restaurantId, productId));
    }

    @Transactional
    public void remove(Long restaurantId, Long productId) {
        ProductPhoto productPhoto = find(restaurantId, productId);

        productRepository.delete(productPhoto);
        productRepository.flush();

        photoStorageService.remove(productPhoto.getFileName());
    }

}

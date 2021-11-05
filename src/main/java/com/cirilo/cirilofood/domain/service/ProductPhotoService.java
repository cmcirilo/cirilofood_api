package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.model.Product;
import com.cirilo.cirilofood.domain.model.ProductPhoto;
import com.cirilo.cirilofood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductPhotoService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto){
        Long restaurantId = productPhoto.getRestaurantId();
        Long productId = productPhoto.getProduct().getId();

        Optional<ProductPhoto> currentProductPhoto = productRepository.findProductPhotoById(restaurantId,productId);

        if (currentProductPhoto.isPresent()){
            productRepository.delete(currentProductPhoto.get());
        }

        return productRepository.save(productPhoto);
    }

}

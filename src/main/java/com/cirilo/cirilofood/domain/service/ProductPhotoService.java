package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.model.ProductPhoto;
import com.cirilo.cirilofood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductPhotoService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto){
        return productRepository.save(productPhoto);
    }

}

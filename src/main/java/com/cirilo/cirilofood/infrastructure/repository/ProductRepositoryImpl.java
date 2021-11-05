package com.cirilo.cirilofood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cirilo.cirilofood.domain.model.ProductPhoto;
import com.cirilo.cirilofood.domain.repository.ProductRepositoryQueries;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public ProductPhoto save(ProductPhoto productPhoto) {
        return manager.merge(productPhoto);
    }

    @Transactional
    @Override
    public void delete(ProductPhoto productPhoto) {
        manager.remove(productPhoto);
    }
}

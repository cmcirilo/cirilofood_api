package com.cirilo.cirilofood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import com.cirilo.cirilofood.domain.repository.CustomJpaRepository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findFirst() {

        var jpql = "from " + getDomainClass().getName();

        T entity = entityManager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();

        return Optional.ofNullable(entity);
    }

    @Override
    public void detach(T entity) {
        entityManager.detach(entity);
    }

}

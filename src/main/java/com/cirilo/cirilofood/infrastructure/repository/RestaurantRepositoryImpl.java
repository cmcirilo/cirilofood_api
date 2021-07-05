package com.cirilo.cirilofood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.repository.RestaurantRepository;
import com.cirilo.cirilofood.domain.repository.RestaurantRepositoryQueries;
import com.cirilo.cirilofood.infrastructure.repository.specification.RestaurantSpecifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

    // using JPQL
    // public List<Restaurant> find(String name, BigDecimal initialShippingFee,
    // BigDecimal finalShippingFee) {

    // var jpql = new StringBuilder();
    // jpql.append("from Restaurant where 0 = 0 ");

    // var parameters = new HashMap<String, Object>();

    // if (StringUtils.hasLength(name)) {
    // jpql.append("and name like :name ");
    // parameters.put("name", "%" + name + "%");
    // }

    // if (initialShippingFee != null) {
    // jpql.append("and taxaFrete >= :taxaInicial ");
    // parameters.put("taxaInicial", initialShippingFee);
    // }

    // if (finalShippingFee != null) {
    // jpql.append("and taxaFrete <= :taxaFinal ");
    // parameters.put("taxaFinal", finalShippingFee);
    // }

    // TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(),
    // Restaurant.class);
    // parameters.forEach((chave, valor) -> query.setParameter(chave, valor));

    // return query.getResultList();
    // }

    // using CriteriaApi
    public List<Restaurant> find(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(name)) {
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if (initialShippingFee != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), initialShippingFee));
        }

        if (finalShippingFee != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), finalShippingFee));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();

    }

    @Override
    public List<Restaurant> findFreeShipping(String name) {
        return restaurantRepository.findAll(
				RestaurantSpecifications.withFreeShipping().and(RestaurantSpecifications.withSimilarName(name)));
    }
}

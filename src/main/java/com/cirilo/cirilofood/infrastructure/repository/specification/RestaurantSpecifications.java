package com.cirilo.cirilofood.infrastructure.repository.specification;

import java.math.BigDecimal;

import com.cirilo.cirilofood.domain.model.Restaurant;

import org.springframework.data.jpa.domain.Specification;

public class RestaurantSpecifications {

    public static Specification<Restaurant> withFreeShipping() {
        return (root, query, builder) -> builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withSimilarName(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

}

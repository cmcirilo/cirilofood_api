package com.cirilo.cirilofood.infrastructure.repository.specification;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.filter.OrderFilter;

public class OrderSpecifications {

    public static Specification<Order> usingFilter(OrderFilter orderFilter) {
        return (root, query, builder) -> {
            if (Order.class.equals(query.getResultType())) {
                root.fetch("restaurant").fetch("cuisine");
                root.fetch("client");
            }

            var predicates = new ArrayList<>();

            if (orderFilter.getClientId() != null) {
                predicates.add(builder.equal(root.get("client"), orderFilter.getClientId()));
            }

            if (orderFilter.getRestaurantId() != null) {
                predicates.add(builder.equal(root.get("restaurant"), orderFilter.getRestaurantId()));
            }

            if (orderFilter.getInitialCreatedDate() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"), orderFilter.getInitialCreatedDate()));
            }

            if (orderFilter.getFinalCreatedDate() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("createdDate"), orderFilter.getFinalCreatedDate()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };

    }

}

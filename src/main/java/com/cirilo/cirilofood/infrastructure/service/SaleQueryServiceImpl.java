package com.cirilo.cirilofood.infrastructure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.model.StatusOrder;
import com.cirilo.cirilofood.domain.model.dto.DailySale;
import com.cirilo.cirilofood.domain.service.SaleQueryService;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DailySale> findDailySales(DailySaleFilter dailySaleFilter, String timeOffSet) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Order.class);
        var predicates = new ArrayList<Predicate>();

        var functionConvertTzCreatedDate = builder.function(
                "convert_tz",
                Date.class,
                root.get("createdDate"),
                builder.literal("+00:00"),
                builder.literal(timeOffSet));

        var functionDateCreatedDate = builder.function(
                "date",
                Date.class,
                functionConvertTzCreatedDate);

        var selection = builder.construct(DailySale.class,
                functionDateCreatedDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalValue")));

        query.select(selection);

        if (dailySaleFilter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant"), dailySaleFilter.getRestaurantId()));
        }

        if (dailySaleFilter.getInitialCreatedDate() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"), dailySaleFilter.getInitialCreatedDate()));
        }

        if (dailySaleFilter.getFinalCreatedDate() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("createdDate"), dailySaleFilter.getFinalCreatedDate()));
        }

        predicates.add(root.get("status").in(StatusOrder.CONFIRMED, StatusOrder.DELIVERED));

        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateCreatedDate);

        return entityManager.createQuery(query).getResultList();
    }
}

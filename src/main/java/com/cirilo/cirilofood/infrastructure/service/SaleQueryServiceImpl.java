package com.cirilo.cirilofood.infrastructure.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.model.dto.DailySale;
import com.cirilo.cirilofood.domain.service.SaleQueryService;
import org.springframework.stereotype.Repository;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DailySale> findDailySales(DailySaleFilter dailySaleFilter) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Order.class);

        var functionDateCreatedDate = builder.function(
                "date",
                Date.class,
                root.get("createdDate"));

        var selection = builder.construct(DailySale.class,
                functionDateCreatedDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalValue")));

        query.select(selection);
        query.groupBy(functionDateCreatedDate);

        return entityManager.createQuery(query).getResultList();
    }
}

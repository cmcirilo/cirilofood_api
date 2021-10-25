package com.cirilo.cirilofood.infrastructure.service;

import java.util.List;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.model.dto.DailySale;
import com.cirilo.cirilofood.domain.service.SaleQueryService;

public class SaleQueryServiceImpl implements SaleQueryService {

    @Override
    public List<DailySale> findDailySales(DailySaleFilter dailySaleFilter) {
        return null;
    }
}

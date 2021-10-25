package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.model.dto.DailySale;

import java.util.List;

public interface SaleQueryService {

    List<DailySale> findDailySales(DailySaleFilter dailySaleFilter);

}

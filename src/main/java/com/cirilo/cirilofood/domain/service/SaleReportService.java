package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;

public interface SaleReportService {

    byte[]  buildDailySales(DailySaleFilter dailySaleFilter, String timeOffSet);
}

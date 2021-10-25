package com.cirilo.cirilofood.infrastructure.service.report;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.service.SaleReportService;
import org.springframework.stereotype.Service;

@Service
public class SaleReportServiceImpl implements SaleReportService {

    @Override
    public byte[] buildDailySales(DailySaleFilter dailySaleFilter, String timeOffSet) {
        return null;
    }
}

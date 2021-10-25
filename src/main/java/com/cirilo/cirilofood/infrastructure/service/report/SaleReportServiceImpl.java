package com.cirilo.cirilofood.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.service.SaleQueryService;
import com.cirilo.cirilofood.domain.service.SaleReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class SaleReportServiceImpl implements SaleReportService {

    @Autowired
    private SaleQueryService saleQueryService;

    @Override
    public byte[] buildDailySales(DailySaleFilter dailySaleFilter, String timeOffSet) {
        try {

            var inputStream = this.getClass()
                    .getResourceAsStream("/reports/daily-sales.jasper");

            var parameters = new HashMap<String, Object>();
            parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var dailySales = saleQueryService.findDailySales(dailySaleFilter, timeOffSet);
            var dataSource = new JRBeanCollectionDataSource(dailySales);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception ex) {
            throw new ReportException("It's not possible generate report at this moment ", ex);
        }
    }
}

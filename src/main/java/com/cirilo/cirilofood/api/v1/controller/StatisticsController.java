package com.cirilo.cirilofood.api.v1.controller;

import java.util.List;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.openapi.controller.StatisticsControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.model.dto.DailySale;
import com.cirilo.cirilofood.domain.service.SaleQueryService;
import com.cirilo.cirilofood.domain.service.SaleReportService;

@RestController
@RequestMapping(path = "/v1/statistics")
public class StatisticsController implements StatisticsControllerOpenApi {

    @Autowired
    private SaleQueryService saleQueryService;

    @Autowired
    private SaleReportService saleReportService;

    @Autowired
    private CiriloLinks ciriloLinks;

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailySale> findDailySales(DailySaleFilter dailySaleFilter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
        return saleQueryService.findDailySales(dailySaleFilter, timeOffSet);
    }

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> findDailySalesPdf(DailySaleFilter dailySaleFilter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {

        byte[] bytes = saleReportService.buildDailySales(dailySaleFilter, timeOffSet);
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytes);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticsModel statistics() {
        var statisticsModel = new StatisticsModel();

        statisticsModel.add(ciriloLinks.linkToStatisticsDailySales("daily-sales"));

        return statisticsModel;
    }

    public static class StatisticsModel extends RepresentationModel<StatisticsModel> {
    }
}

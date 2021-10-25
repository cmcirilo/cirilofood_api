package com.cirilo.cirilofood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.model.dto.DailySale;
import com.cirilo.cirilofood.domain.service.SaleQueryService;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController {

    @Autowired
    private SaleQueryService saleQueryService;

    @GetMapping("/daily-sales")
    public List<DailySale> findDailySales(DailySaleFilter dailySaleFilter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
        return saleQueryService.findDailySales(dailySaleFilter, timeOffSet);
    }
}

package com.cirilo.cirilofood.api.controller;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.model.dto.DailySale;
import com.cirilo.cirilofood.domain.service.SaleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path ="/statistics")
public class StatisticsController {

    @Autowired
    private SaleQueryService saleQueryService;

    @GetMapping("/daily-sales")
    public List<DailySale> findDailySales(DailySaleFilter dailySaleFilter){
        return saleQueryService.findDailySales(dailySaleFilter);
    }
}

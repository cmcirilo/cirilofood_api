package com.cirilo.cirilofood.api.v1.openapi.controller;

import java.util.List;

import com.cirilo.cirilofood.api.v1.controller.StatisticsController;
import org.springframework.http.ResponseEntity;

import com.cirilo.cirilofood.domain.filter.DailySaleFilter;
import com.cirilo.cirilofood.domain.model.dto.DailySale;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Statistics")
public interface StatisticsControllerOpenApi {

    @ApiOperation(value = "Statistics", hidden = true)
    StatisticsController.StatisticsModel statistics();

    @ApiOperation("Statistics Daily Sales")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "restaurantId", value = "Restaurant Id",
                example = "1", dataType = "int"),
        @ApiImplicitParam(name = "dateInitial", value = "Creation date initial order",
                example = "2019-12-01T00:00:00Z", dataType = "date-time"),
        @ApiImplicitParam(name = "dateFinal", value = "Creation date final order",
                example = "2019-12-02T23:59:59Z", dataType = "date-time")
    })
    List<DailySale> findDailySales(
            DailySaleFilter dailySaleFilter,
            @ApiParam(value = "Time shift to be considered in the consultation in relation to UTC",
                    defaultValue = "+00:00") String timeOffSet);

    ResponseEntity<byte[]> findDailySalesPdf(DailySaleFilter dailySaleFilter,
            String timeOffSet);
}

package com.cirilo.cirilofood.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class DailySale {

    private LocalDate date;

    private Long totalSale;

    private BigDecimal totalBilling;

}

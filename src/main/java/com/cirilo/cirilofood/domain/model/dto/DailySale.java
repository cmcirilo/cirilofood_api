package com.cirilo.cirilofood.domain.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DailySale {

    private Date createdDate;

    private Long totalSale;

    private BigDecimal totalBilling;

}

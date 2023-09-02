package com.cirilo.cirilofood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFilter {

    @ApiModelProperty(example = "1", value = "Client Id to filter")
    private Long clientId;

    @ApiModelProperty(example = "1", value = "Restaurant Id to filter")
    private Long restaurantId;

    @ApiModelProperty(example = "2019-10-30T00:00:00Z",
            value = "Date/Time Created Initial to filter")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime initialCreatedDate;

    @ApiModelProperty(example = "2019-11-01T10:00:00Z",
            value = "Date/Time Created Final to filter")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime finalCreatedDate;

}

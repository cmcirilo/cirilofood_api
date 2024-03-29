package com.cirilo.cirilofood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantInput {

    @ApiModelProperty(example = "Thai Gourmet", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "12.00", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal shippingFee;

    @Valid
    @NotNull
    private CuisineIdInput cuisine;

    @Valid
    @NotNull
    private AddressInput address;

}

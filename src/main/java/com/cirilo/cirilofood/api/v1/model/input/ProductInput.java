package com.cirilo.cirilofood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductInput {

    @ApiModelProperty(example = "Baby Beef", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "With rice and bean", required = true)
    @NotBlank
    private String description;

    @ApiModelProperty(example = "25.50", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @ApiModelProperty(example = "true", required = true)
    @NotNull
    private Boolean active;
}

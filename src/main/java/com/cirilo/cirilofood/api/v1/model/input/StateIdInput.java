package com.cirilo.cirilofood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StateIdInput {

    @ApiModelProperty(value="State Id", example = "1", required = true)
    @NotNull
    private Long id;
}

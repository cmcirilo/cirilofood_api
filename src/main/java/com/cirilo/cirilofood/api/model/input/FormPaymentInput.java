package com.cirilo.cirilofood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class FormPaymentInput {

    @NotBlank
    private String description;

}

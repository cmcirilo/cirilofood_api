package com.cirilo.cirilofood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.cirilo.cirilofood.api.v1.model.FormPaymentModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("FormsPaymentModel")
public class FormsPaymentModelOpenApi {

    private FormsPaymentEmbeddedModelOpenApi _embedded;

    private Links _links;

    @Data
    @ApiModel("FormsPaymentEmbeddedModel")
    public class FormsPaymentEmbeddedModelOpenApi {

        private List<FormPaymentModel> formsPayment;

    }
}

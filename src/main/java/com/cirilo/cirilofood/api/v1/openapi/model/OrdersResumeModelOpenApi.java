package com.cirilo.cirilofood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.cirilo.cirilofood.api.v1.model.OrderResumeModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("OrdersResumeModel")
public class OrdersResumeModelOpenApi {

    private OrdersResumeEmbeddedModelOpenApi _embedded;

    private Links _links;

    private PageModelOpenApi page;

    @Data
    @ApiModel("OrdersResumeEmbeddedModel")
    public class OrdersResumeEmbeddedModelOpenApi {

        private List<OrderResumeModel> orders;

    }

}

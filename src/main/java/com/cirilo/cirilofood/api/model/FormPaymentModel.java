package com.cirilo.cirilofood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "formsPayment")
@Getter
@Setter
public class FormPaymentModel  extends RepresentationModel<FormPaymentModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Credit Card")
    private String description;

}

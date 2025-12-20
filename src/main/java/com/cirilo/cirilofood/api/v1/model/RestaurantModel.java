package com.cirilo.cirilofood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class RestaurantModel extends RepresentationModel<RestaurantModel> {

    @ApiModelProperty(example = "1")
    // @JsonView({RestaurantView.Resume.class, RestaurantView.OnlyName.class})
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    // @JsonView({RestaurantView.Resume.class, RestaurantView.OnlyName.class})
    private String name;

    @ApiModelProperty(example = "12.00")
    // @JsonView(RestaurantView.Resume.class)
    private BigDecimal shippingFee;

    // @JsonView(RestaurantView.Resume.class)
    private CuisineModel cuisine;

    private Boolean active;

    private Boolean opened;

    private AddressModel address;

}

package com.cirilo.cirilofood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.cirilo.cirilofood.api.v1.model.StateModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cities")
@ApiModel(value ="City Model", description = "Represents City Model")
@Getter
@Setter
public class CityModelV2 extends RepresentationModel<CityModelV2> {

    @ApiModelProperty(value="City Id", example = "1")
    private Long idCity;

    @ApiModelProperty(value="City Name", example = "São Paulo")
    private String nameCity;

    private Long idState;

    private String nameState;

}

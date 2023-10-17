package com.cirilo.cirilofood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class StateModel extends RepresentationModel<StateModel> {

    @ApiModelProperty(value="State Id", example = "1")
    private Long id;

    @ApiModelProperty(value="State Name", example = "São Paulo")
    private String name;

}

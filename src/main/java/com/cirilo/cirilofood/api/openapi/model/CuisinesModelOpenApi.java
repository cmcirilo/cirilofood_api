package com.cirilo.cirilofood.api.openapi.model;

import java.util.List;

import com.cirilo.cirilofood.api.model.CuisineModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CuisinesModel")
public class CuisinesModelOpenApi extends PagedModelOpenApi<CuisineModel> {

}

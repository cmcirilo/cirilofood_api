package com.cirilo.cirilofood.api.model;

import com.cirilo.cirilofood.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuisineModel {

    @JsonView(RestaurantView.Resume.class)
    private Long id;

    @JsonView(RestaurantView.Resume.class)
    private String name;

}

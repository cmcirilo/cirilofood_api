package com.cirilo.cirilofood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.cirilo.cirilofood.domain.model.Restaurant;

public interface RestaurantRepositoryQueries {

    List<Restaurant> find(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee);

    List<Restaurant> findFreeShipping(String name);

}

package com.cirilo.cirilofood.domain.repository;

import com.cirilo.cirilofood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository
        extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {

//    @Query("select distinct r from Restaurant r join r.cuisine left join fetch r.formasPagamento")
    @Query("select distinct r from Restaurant r join r.cuisine")
    List<Restaurant> findAll();

    // prefixs - readBy, streamBy, getBy, findBy, queryBy
    List<Restaurant> queryByShippingFeeBetween(BigDecimal initialShippingFee, BigDecimal finalShippingFee);

    // @Query("from Restaurant where name like %:name% and cuisine.id= :id") using
    // resource/meta-inf/orm.xml
    List<Restaurant> findByNameAndCuisine(String name, @Param("id") Long cuisine);

    List<Restaurant> findByNameContainingAndCuisineId(String name, Long cuisineId);

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

    List<Restaurant> findTop2RestaurantByNameContaining(String name);

    int countByCuisineId(Long cuisineId);

}

package com.cirilo.cirilofood.domain.repository;

import com.cirilo.cirilofood.domain.model.Product;
import com.cirilo.cirilofood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries {

    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId,
                               @Param("product") Long productId);

    List<Product> findAllByRestaurant(Restaurant restaurant);

    @Query("from Product p where p.active = true and restaurant = :restaurant")
    List<Product> findActivesByRestaurant(Restaurant restaurant);
}

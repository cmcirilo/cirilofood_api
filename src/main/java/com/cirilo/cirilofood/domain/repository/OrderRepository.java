package com.cirilo.cirilofood.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cirilo.cirilofood.domain.model.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Optional<Order> findByCode(String code);

    @Query("from Order p join fetch p.client join fetch p.restaurant r join fetch r.cuisine")
    List<Order> findAll();
}

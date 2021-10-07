package com.cirilo.cirilofood.domain.repository;

import org.springframework.stereotype.Repository;

import com.cirilo.cirilofood.domain.model.Order;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, Long> {
}

package com.cirilo.cirilofood.domain.repository;

import java.util.List;
import java.util.Optional;

import com.cirilo.cirilofood.domain.model.Cuisine;

import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends CustomJpaRepository<Cuisine, Long> {

    List<Cuisine> findAllByNameContaining(String name);

    Optional<Cuisine> findByName(String name);

    boolean existsByName(String name);

}

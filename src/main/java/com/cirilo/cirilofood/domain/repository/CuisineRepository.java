package com.cirilo.cirilofood.domain.repository;

import java.util.List;
import java.util.Optional;

import com.cirilo.cirilofood.domain.model.Cuisine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends CustomJpaRepository<Cuisine, Long> {

    @Query(value = "from Cuisine c join fetch c.restaurants r where c.name like :name",
            countQuery = "select count(c.id) from Cuisine c where c.name like :name")
    Page<Cuisine> findAllByName(Pageable pageable, @Param("nome") String name);

    List<Cuisine> findAllByNameContaining(String name);

    Optional<Cuisine> findByName(String name);

    boolean existsByName(String name);

}

package com.cirilo.cirilofood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cirilo.cirilofood.domain.model.Restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {

    // prefixs - readBy, streamBy, getBy, findBy, queryBy
    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    // @Query("from Restaurante where nome like %:nome% and cozinha.id= :id") usando resource/meta-inf/orm.xml
    List<Restaurante> consultarPorNomeECozinha(String nome, @Param("id") Long cozinha);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2RestauranteByNomeContaining(String nome);

    int countByCozinhaId(Long cozinhaId);

}

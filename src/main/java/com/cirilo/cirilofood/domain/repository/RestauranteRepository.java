package com.cirilo.cirilofood.domain.repository;

import com.cirilo.cirilofood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository
        extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

//    @Query("select distinct r from Restaurante r join r.cozinha left join fetch r.formasPagamento")
    @Query("select distinct r from Restaurante r join r.cozinha")
    List<Restaurante> findAll();

    // prefixs - readBy, streamBy, getBy, findBy, queryBy
    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    // @Query("from Restaurante where nome like %:nome% and cozinha.id= :id") usando
    // resource/meta-inf/orm.xml
    List<Restaurante> consultarPorNomeECozinha(String nome, @Param("id") Long cozinha);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2RestauranteByNomeContaining(String nome);

    int countByCozinhaId(Long cozinhaId);

}

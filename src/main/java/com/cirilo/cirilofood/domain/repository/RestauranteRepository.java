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

//    @Query("select distinct r from Restaurante r join r.cuisine left join fetch r.formasPagamento")
    @Query("select distinct r from Restaurante r join r.cuisine")
    List<Restaurante> findAll();

    // prefixs - readBy, streamBy, getBy, findBy, queryBy
    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    // @Query("from Restaurante where nome like %:nome% and cuisine.id= :id") usando
    // resource/meta-inf/orm.xml
    List<Restaurante> consultarPorNomeECuisine(String nome, @Param("id") Long cuisine);

    List<Restaurante> findByNomeContainingAndCuisineId(String nome, Long cuisineId);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2RestauranteByNomeContaining(String nome);

    int countByCuisineId(Long cuisineId);

}

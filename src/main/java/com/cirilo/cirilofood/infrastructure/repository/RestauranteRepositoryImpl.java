package com.cirilo.cirilofood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.cirilo.cirilofood.domain.model.Restaurante;

import org.springframework.stereotype.Repository;

@Repository
public class RestauranteRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    // using JPQL
    // public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

    // var jpql = new StringBuilder();
    // jpql.append("from Restaurante where 0 = 0 ");

    // var parametros = new HashMap<String, Object>();

    // if (StringUtils.hasLength(nome)) {
    // jpql.append("and nome like :nome ");
    // parametros.put("nome", "%" + nome + "%");
    // }

    // if (taxaFreteInicial != null) {
    // jpql.append("and taxaFrete >= :taxaInicial ");
    // parametros.put("taxaInicial", taxaFreteInicial);
    // }

    // if (taxaFreteFinal != null) {
    // jpql.append("and taxaFrete <= :taxaFinal ");
    // parametros.put("taxaFinal", taxaFreteFinal);
    // }

    // TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
    // parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

    // return query.getResultList();
    // }

    // using CriteriaApi
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        criteria.from(Restaurante.class); // from Restaurante

        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();

    }
}

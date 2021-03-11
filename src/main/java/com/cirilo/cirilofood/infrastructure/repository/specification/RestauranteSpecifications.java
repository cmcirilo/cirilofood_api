package com.cirilo.cirilofood.infrastructure.repository.specification;

import java.math.BigDecimal;

import com.cirilo.cirilofood.domain.model.Restaurante;

import org.springframework.data.jpa.domain.Specification;

public class RestauranteSpecifications {

    public static Specification<Restaurante> comFreteGratis() {
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
    }

}

package com.cirilo.cirilofood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cirilo.cirilofood.domain.model.Cozinha;

import org.springframework.stereotype.Component;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;
    
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class)
            .getResultList();
    }

    public Cozinha adicionar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }
    
}

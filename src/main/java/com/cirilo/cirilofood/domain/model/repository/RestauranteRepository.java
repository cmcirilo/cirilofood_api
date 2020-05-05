package com.cirilo.cirilofood.domain.model.repository;

import java.util.List;

import com.cirilo.cirilofood.domain.model.Restaurante;

public interface RestauranteRepository {
    
    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante restaurante);

    void remover(Restaurante restaurante);
    
}
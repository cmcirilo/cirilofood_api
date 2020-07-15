package com.cirilo.cirilofood.domain.repository;

import java.util.List;

import com.cirilo.cirilofood.domain.model.Restaurante;

public interface RestauranteRepository {

    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante restaurante);

    void remover(Long id);

}

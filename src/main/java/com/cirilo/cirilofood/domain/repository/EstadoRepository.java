package com.cirilo.cirilofood.domain.repository;

import java.util.List;

import com.cirilo.cirilofood.domain.model.Estado;

public interface EstadoRepository {

    List<Estado> listar();

    Estado buscar(Long id);

    Estado salvar(Estado estado);

    void remover(Long id);

}

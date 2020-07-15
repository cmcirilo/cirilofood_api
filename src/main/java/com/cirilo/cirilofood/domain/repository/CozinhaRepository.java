package com.cirilo.cirilofood.domain.repository;

import java.util.List;

import com.cirilo.cirilofood.domain.model.Cozinha;

public interface CozinhaRepository {

    List<Cozinha> listar();

    Cozinha buscar(long id);

    Cozinha salvar(Cozinha cozinha);

    void remover(Long id);

}

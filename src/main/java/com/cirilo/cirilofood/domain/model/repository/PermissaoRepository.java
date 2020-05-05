package com.cirilo.cirilofood.domain.model.repository;

import java.util.List;

import com.cirilo.cirilofood.domain.model.Permissao;

public interface PermissaoRepository {

    List<Permissao> listar();

    Permissao buscar(Long id);

    Permissao salvar(Permissao permissao);

    void remover(Permissao permissao);

}
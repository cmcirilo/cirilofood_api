package com.cirilo.cirilofood.domain.service;

import java.util.List;

import com.cirilo.cirilofood.domain.exception.EntidadeNaoEncontradaException;
import com.cirilo.cirilofood.domain.model.Cozinha;
import com.cirilo.cirilofood.domain.model.Restaurante;
import com.cirilo.cirilofood.domain.repository.CozinhaRepository;
import com.cirilo.cirilofood.domain.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Long id) {
        try {
            return restauranteRepository.buscar(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurante com código %d", id));
        }
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com o código %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);
        
        return restauranteRepository.salvar(restaurante);
    }
}

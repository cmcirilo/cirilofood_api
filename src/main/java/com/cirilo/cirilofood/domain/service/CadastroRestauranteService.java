package com.cirilo.cirilofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirilo.cirilofood.domain.exception.EntidadeNaoEncontradaException;
import com.cirilo.cirilofood.domain.model.Cozinha;
import com.cirilo.cirilofood.domain.model.Restaurante;
import com.cirilo.cirilofood.domain.repository.CozinhaRepository;
import com.cirilo.cirilofood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
}

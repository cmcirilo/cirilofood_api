package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.exception.RestauranteNaoEncontradoException;
import com.cirilo.cirilofood.domain.model.Cuisine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirilo.cirilofood.domain.model.Restaurante;
import com.cirilo.cirilofood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CuisineService cuisineService;

	public Restaurante salvar(Restaurante restaurante) {
		Long cuisineId = restaurante.getCuisine().getId();

		Cuisine cuisine = cuisineService.find(cuisineId);

		restaurante.setCuisine(cuisine);

		return restauranteRepository.save(restaurante);
	}

	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}
}

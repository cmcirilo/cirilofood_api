package com.cirilo.cirilofood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.model.Restaurante;
import com.cirilo.cirilofood.domain.repository.CuisineRepository;
import com.cirilo.cirilofood.domain.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CuisineRepository cuisineRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorNome(String nome, Long cuisineId) {
		return restauranteRepository.consultarPorNomeECuisine(nome, cuisineId);
		// return restauranteRepository.findByNomeContainingAndCuisineId(nome,
		// cuisineId);
	}

	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> restaurantePrimeiroPorNome(String nome) {
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}

	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> restaurantesTop2PorNome(String nome) {
		return restauranteRepository.findTop2RestauranteByNomeContaining(nome);
	}

	@GetMapping("cuisines/exists")
	public boolean cuisineExists(String nome) {
		return cuisineRepository.existsByName(nome);
	}

	@GetMapping("restaurantes/count-por-cuisine")
	public int restaurantesCountPorcuisine(Long cuisineId) {
		return restauranteRepository.countByCuisineId(cuisineId);
	}

	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}

	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {

		return restauranteRepository.findComFreteGratis(nome);
	}

	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}

	@GetMapping("/cuisines/primeira")
	public Optional<Cuisine> cuisinePrimeiro() {
		return cuisineRepository.buscarPrimeiro();
	}
}

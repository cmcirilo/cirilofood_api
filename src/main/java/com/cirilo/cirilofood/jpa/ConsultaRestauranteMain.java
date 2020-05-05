package com.cirilo.cirilofood.jpa;

import java.util.List;

import com.cirilo.cirilofood.CirilofoodApiApplication;
import com.cirilo.cirilofood.domain.model.Restaurante;
import com.cirilo.cirilofood.domain.model.repository.RestauranteRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(CirilofoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

		List<Restaurante> restaurantes = restauranteRepository.listar();

		for (Restaurante restaurante : restaurantes) {
			System.out.printf("%s - %f - %s \n", restaurante.getNome(), restaurante.getTaxafrete(),
					restaurante.getCozinha().getNome());
		}
	}

}

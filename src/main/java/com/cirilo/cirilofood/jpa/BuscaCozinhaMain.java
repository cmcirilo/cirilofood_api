package com.cirilo.cirilofood.jpa;

import com.cirilo.cirilofood.CirilofoodApiApplication;
import com.cirilo.cirilofood.domain.model.Cozinha;
import com.cirilo.cirilofood.domain.model.repository.CozinhaRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(CirilofoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

		Cozinha cozinha = cozinhaRepository.buscar(1L);

		System.out.println(cozinha.getNome());

	}

}

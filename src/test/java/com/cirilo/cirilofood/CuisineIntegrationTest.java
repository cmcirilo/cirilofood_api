package com.cirilo.cirilofood;

import com.cirilo.cirilofood.domain.model.Cozinha;
import com.cirilo.cirilofood.domain.service.CozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineIntegrationTest {

	@Autowired
	private CozinhaService cuisineService;

	@Test
	public void testCreateCuisineWithSuccess() {
		//scenario
		Cozinha cuisine = new Cozinha();
		cuisine.setNome("Chinese");

		//action
		cuisine = cuisineService.salvar(cuisine);

		//validation
		assertThat(cuisine).isNotNull();
		assertThat(cuisine.getId()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreateCuisineWithoutName(){
		Cozinha cuisine = new Cozinha();
		cuisine.setNome(null);

		cuisine = cuisineService.salvar(cuisine);
	}

}

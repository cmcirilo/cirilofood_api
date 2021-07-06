package com.cirilo.cirilofood;

import com.cirilo.cirilofood.domain.exception.CuisineNotFoundException;
import com.cirilo.cirilofood.domain.exception.EntityInUseException;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.service.CuisineService;
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
	private CuisineService cuisineService;

	@Test
	public void shouldAssignCuisineId_WhenCreateCuisineWithCorrectData() {
		//scenario
		Cuisine cuisine = new Cuisine();
		cuisine.setName("Chinese");

		//action
		cuisine = cuisineService.save(cuisine);

		//validation
		assertThat(cuisine).isNotNull();
		assertThat(cuisine.getId()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void shouldFail_WhenCreateCuisineWithoutName(){
		Cuisine cuisine = new Cuisine();
		cuisine.setName(null);

		cuisine = cuisineService.save(cuisine);
	}

	@Test(expected = EntityInUseException.class)
	public void shouldFail_WhenDeleteCuisineInUse(){
		cuisineService.delete(1L);
	}

	@Test(expected = CuisineNotFoundException.class)
	public void shouldFail_WhenDeleteCuisineNotExistent(){
		cuisineService.delete(100L);
	}

}

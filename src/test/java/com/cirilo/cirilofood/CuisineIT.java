package com.cirilo.cirilofood;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.cirilo.cirilofood.domain.exception.CuisineNotFoundException;
import com.cirilo.cirilofood.domain.exception.EntityInUseException;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.service.CuisineService;

import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CuisineIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cuisines";

        flyway.migrate();
    }

    @Test
    public void shouldAssignCuisineId_WhenCreateCuisineWithCorrectData() {
        // scenario
        Cuisine cuisine = new Cuisine();
        cuisine.setName("Chinese");

        // action
        cuisine = cuisineService.save(cuisine);

        // validation
        assertThat(cuisine).isNotNull();
        assertThat(cuisine.getId()).isNotNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldFail_WhenCreateCuisineWithoutName() {
        Cuisine cuisine = new Cuisine();
        cuisine.setName(null);

        cuisine = cuisineService.save(cuisine);
    }

    @Test(expected = EntityInUseException.class)
    public void shouldFail_WhenDeleteCuisineInUse() {
        cuisineService.delete(1L);
    }

    @Test(expected = CuisineNotFoundException.class)
    public void shouldFail_WhenDeleteCuisineNotExistent() {
        cuisineService.delete(100L);
    }

    @Test
    public void shouldReturnStatus200_WhenListCuisines() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void shouldReturnFourCuisines_WhenListCuisines() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize(4))
            .body("name", hasItems("Indian", "Thay"));

    }

    @Test
    public void shouldReturnStatus201_WhenCreatedCuisine(){
        given()
            .body("{ \"name\": \"German\"  }")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

}

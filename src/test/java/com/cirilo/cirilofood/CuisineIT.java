package com.cirilo.cirilofood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.repository.CuisineRepository;
import com.cirilo.cirilofood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CuisineIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cuisines";

        databaseCleaner.clearTables();
        prepareData();
    }

    private void prepareData() {
        Cuisine cuisine1 = new Cuisine();
        cuisine1.setName("Thay");
        cuisineRepository.save(cuisine1);

        Cuisine cuisine2 = new Cuisine();
        cuisine2.setName("American");
        cuisineRepository.save(cuisine2);
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
    public void shouldReturnTwoCuisines_WhenListCuisines() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize(2))
            .body("name", hasItems("American", "Thay"));
    }

    @Test
    public void shouldReturnStatus201_WhenCreatedCuisine() {
        given()
            .body("{ \"name\": \"German\"  }")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturnResponseAndStatus_WhenGetCuisineExist() {
        given()
            .pathParam("cuisineId",2)
            .accept(ContentType.JSON)
        .when()
            .get("/{cuisineId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo("American"));
    }
    @Test
    public void shouldReturnStatus404_WhenGetCuisineNotFound() {
        given()
            .pathParam("cuisineId",100)
            .accept(ContentType.JSON)
        .when()
            .get("/{cuisineId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

}

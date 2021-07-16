package com.cirilo.cirilofood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
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
import com.cirilo.cirilofood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CuisineIT {

    private static final int CUISINE_ID_NOT_FOUND = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CuisineRepository cuisineRepository;

    private Cuisine americanCuisine;

    private int quantityCuisinesCreated;

    private String jsonCorrectChineseCuisine;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cuisines";

        databaseCleaner.clearTables();
        prepareData();

        jsonCorrectChineseCuisine = ResourceUtils
                .getContentFromResource("/json/correct/chinese-cuisine.json");
    }

    private void prepareData() {
        Cuisine thayCuisine = new Cuisine();
        thayCuisine.setName("Thay");
        cuisineRepository.save(thayCuisine);

        americanCuisine = new Cuisine();
        americanCuisine.setName("American");
        cuisineRepository.save(americanCuisine);

        quantityCuisinesCreated = (int) cuisineRepository.count();
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
    public void shouldReturnCorrectQuantityCuisines_WhenListCuisines() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(quantityCuisinesCreated));
    }

    @Test
    public void shouldReturnStatus201_WhenCreatedCuisine() {
        given()
                .body(jsonCorrectChineseCuisine)
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
                .pathParam("cuisineId", americanCuisine.getId())
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
                .pathParam("cuisineId", CUISINE_ID_NOT_FOUND)
                .accept(ContentType.JSON)
                .when()
                .get("/{cuisineId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}

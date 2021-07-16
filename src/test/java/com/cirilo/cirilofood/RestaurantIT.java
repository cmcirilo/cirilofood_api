package com.cirilo.cirilofood;

import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.repository.CuisineRepository;
import com.cirilo.cirilofood.domain.repository.RestaurantRepository;
import com.cirilo.cirilofood.util.DatabaseCleaner;
import com.cirilo.cirilofood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestaurantIT {

    private static final String VIOLATION_RULE_PROBLEM_TYPE = "Violation Rule";

    private static final String DATA_INCORRECT_PROBLEM_TITLE = "Invalid Data";

    private static final int RESTAURANT_ID_NOT_FOUND = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private String jsonRestaurantCorrect;
    private String jsonRestaurantWithoutShippingFee;
    private String jsonRestaurantWithoutCuisine;
    private String jsonRestaurantWithCuisineNotFound;

    private Restaurant burgerTopRestaurant;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurants";

        jsonRestaurantCorrect = ResourceUtils.getContentFromResource(
                "/json/correct/restaurant-new-york-barbecue.json");

        jsonRestaurantWithoutShippingFee = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-new-york-barbecue-without-shipping-fee.json");

        jsonRestaurantWithoutCuisine = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-new-york-barbecue-without-cuisine.json");

        jsonRestaurantWithCuisineNotFound = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-new-york-barbecue-with-cuisine-not-found.json");

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void shouldReturn200_WhenListRestaurants() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnStatus201_WhenRestaurantCreated() {
        given()
                .body(jsonRestaurantCorrect)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturn400_WhenRestaurantWithoutShippingFee() {
        given()
                .body(jsonRestaurantWithoutShippingFee)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DATA_INCORRECT_PROBLEM_TITLE));
    }

    @Test
    public void shouldReturnStatus400_WhenRestaurantWithoutCuisine() {
        given()
                .body(jsonRestaurantWithoutCuisine)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DATA_INCORRECT_PROBLEM_TITLE));
    }

    @Test
    public void shouldReturnStatus400_WhenRestaurantWithCuisineNotFound() {
        given()
                .body(jsonRestaurantWithCuisineNotFound)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(VIOLATION_RULE_PROBLEM_TYPE));
    }

    @Test
    public void shouldReturnResponseAndCorrectStatus_WhenGetRestaurant() {
        given()
                .pathParam("restaurantId", burgerTopRestaurant.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{restaurantId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(burgerTopRestaurant.getName()));
    }

    @Test
    public void shouldReturnStatus404_WhenGetRestaurantNotFound() {
        given()
                .pathParam("restaurantId", RESTAURANT_ID_NOT_FOUND)
                .accept(ContentType.JSON)
                .when()
                .get("/{restaurantId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData() {
        Cuisine brazilianCuisine = new Cuisine();
        brazilianCuisine.setName("Brazilian");
        cuisineRepository.save(brazilianCuisine);

        Cuisine americanCuisine = new Cuisine();
        americanCuisine.setName("American");
        cuisineRepository.save(americanCuisine);

        burgerTopRestaurant = new Restaurant();
        burgerTopRestaurant.setName("Burger Top");
        burgerTopRestaurant.setShippingFee(new BigDecimal(10));
        burgerTopRestaurant.setCuisine(americanCuisine);
        restaurantRepository.save(burgerTopRestaurant);

        Restaurant farmRestaurant = new Restaurant();
        farmRestaurant.setName("Farm food");
        farmRestaurant.setShippingFee(new BigDecimal(10));
        farmRestaurant.setCuisine(brazilianCuisine);
        restaurantRepository.save(farmRestaurant);
    }
}
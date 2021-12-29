package com.cirilo.cirilofood.client;

import com.cirilo.cirilofood.client.api.ClientApiException;
import com.cirilo.cirilofood.client.api.RestaurantClient;
import com.cirilo.cirilofood.client.model.RestaurantModel;
import com.cirilo.cirilofood.client.model.input.AddressInput;
import com.cirilo.cirilofood.client.model.input.CityIdInput;
import com.cirilo.cirilofood.client.model.input.CuisineIdInput;
import com.cirilo.cirilofood.client.model.input.RestaurantInput;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class CreateRestaurantMain {

    public static void main(String[] args) {
        try {
            var restTemplate = new RestTemplate();
            var restaurantClient = new RestaurantClient(
                    restTemplate, "http://localhost:8080");

            var cuisineIdInput = new CuisineIdInput();
            cuisineIdInput.setId(1L);

            var cityIdInput = new CityIdInput();
            cityIdInput.setId(1L);

            var addressInput = new AddressInput();
            addressInput.setCity(cityIdInput);
            addressInput.setZipCode("38500-111");
            addressInput.setStreet("Rua Xyz");
            addressInput.setNumber("300");
            addressInput.setDistrict("Centro");

            var restaurantInput = new RestaurantInput();
            restaurantInput.setName("Comida Mineira");
            restaurantInput.setShippingFee(new BigDecimal(9.5));
            restaurantInput.setCuisine(new CuisineIdInput());
            restaurantInput.setCuisine(cuisineIdInput);
            restaurantInput.setAddress(addressInput);

            RestaurantModel restaurantModel = restaurantClient.create(restaurantInput);

            System.out.println(restaurantModel);
        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem().getUserMessage());

                e.getProblem().getObjects().stream()
                        .forEach(p -> System.out.println("- " + p.getUserMessage()));

            } else {
                System.out.println("Unknown error");
                e.printStackTrace();
            }
        }
    }
}

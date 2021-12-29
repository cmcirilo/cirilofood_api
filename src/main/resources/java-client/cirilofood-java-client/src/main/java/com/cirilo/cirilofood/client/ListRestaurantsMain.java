package com.cirilo.cirilofood.client;

import com.cirilo.cirilofood.client.api.ClientApiException;
import com.cirilo.cirilofood.client.api.RestaurantClient;
import org.springframework.web.client.RestTemplate;

public class ListRestaurantsMain {

    public static void main(String[] args) {
        try {

            RestTemplate restTemplate = new RestTemplate();

            RestaurantClient restaurantClient = new RestaurantClient(restTemplate, "http://localhost:8080");

            restaurantClient.list().forEach(System.out::println);

        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem());
                System.out.println(e.getProblem().getUserMessage());
            } else {
                System.out.println("Unknown error");
                e.printStackTrace();
            }
        }

    }
}

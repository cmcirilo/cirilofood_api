package com.cirilo.cirilofood.client;

import com.cirilo.cirilofood.client.api.RestaurantClient;
import org.springframework.web.client.RestTemplate;

public class ListRestaurantsMain {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        RestaurantClient restaurantClient = new RestaurantClient(restTemplate, "http://localhost:8080");

        restaurantClient.list().forEach(System.out::println);
        
    }
}

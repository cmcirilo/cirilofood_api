package com.cirilo.cirilofood.client.api;

import com.cirilo.cirilofood.client.model.RestaurantResumeModel;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestaurantClient {

    private static final String RESOURCE_PATH = "/restaurantszz";

    private RestTemplate restTemplate;

    private String url;

    public List<RestaurantResumeModel> list() {

        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);
            RestaurantResumeModel[] restaurants = restTemplate
                    .getForObject(resourceUri, RestaurantResumeModel[].class);

            return Arrays.asList(restaurants);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }

    }
}

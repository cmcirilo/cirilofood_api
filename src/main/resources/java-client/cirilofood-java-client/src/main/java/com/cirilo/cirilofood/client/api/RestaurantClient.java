package com.cirilo.cirilofood.client.api;

import com.cirilo.cirilofood.client.model.RestaurantModel;
import com.cirilo.cirilofood.client.model.RestaurantResumeModel;
import com.cirilo.cirilofood.client.model.input.RestaurantInput;
import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestaurantClient {

    private static final String RESOURCE_PATH = "/restaurants";

    private RestTemplate restTemplate;

    private String url;

    public List<RestaurantResumeModel> list() {
        URI resourceUri = URI.create(url + RESOURCE_PATH);

        try {
            RestaurantResumeModel[] restaurants = restTemplate
                    .getForObject(resourceUri, RestaurantResumeModel[].class);

            return Arrays.asList(restaurants);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }

    }

    public RestaurantModel create(RestaurantInput restaurant) {
        var resourceUri = URI.create(url + RESOURCE_PATH);

        try {
            return restTemplate
                    .postForObject(resourceUri, restaurant, RestaurantModel.class);
        } catch (HttpClientErrorException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }
}

package com.cirilo.cirilofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cirilo.cirilofood.domain.exception.RestaurantNotFoundException;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.model.FormPayment;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private CityService cityService;

    @Autowired
    private FormPaymentService formPaymentService;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        Cuisine cuisine = cuisineService.find(cuisineId);
        City city = cityService.find(cityId);

        restaurant.setCuisine(cuisine);
        restaurant.getAddress().setCity(city);

        return restaurantRepository.save(restaurant);
    }

    public Restaurant find(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    @Transactional
    public void activate(Long restaurantId) {
        Restaurant currentRestaurant = find(restaurantId);

        currentRestaurant.activate();
        // currentRestaurant.setActive(Boolean.TRUE);

        // Itś not necessary the save instruction (restaurantRepository.save(restaurant))
        // because JPA manages and will synchronize with the database
    }

    @Transactional
    public void desactivate(Long restaurantId) {
        Restaurant currentRestaurant = find(restaurantId);

        currentRestaurant.desactivate();
    }

    @Transactional
    public void disassociateFormPayment(Long restaurantId, Long formPaymentId) {
        Restaurant restaurant = find(restaurantId);
        FormPayment formPayment = formPaymentService.find(formPaymentId);

        restaurant.disassociateFormPayment(formPayment);
    }

    @Transactional
    public void associateFormPayment(Long restaurantId, Long formPaymentId) {
        Restaurant restaurant = find(restaurantId);
        FormPayment formPayment = formPaymentService.find(formPaymentId);

        restaurant.associateFormPayment(formPayment);
    }
}

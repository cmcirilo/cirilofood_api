package com.cirilo.cirilofood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.repository.CuisineRepository;
import com.cirilo.cirilofood.domain.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private CuisineRepository cuisineRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@GetMapping("/restaurants/by-shipping-fee")
	public List<Restaurant> restaurantsByShippingFee(BigDecimal initialShippingFee, BigDecimal finalShippingFee) {
		return restaurantRepository.queryByShippingFeeBetween(initialShippingFee, finalShippingFee);
	}

	@GetMapping("/restaurants/por-name")
	public List<Restaurant> restaurantsByName(String name, Long cuisineId) {
		return restaurantRepository.findByNameAndCuisine(name, cuisineId);
		// return restaurantRepository.findByNameContainingAndCuisineId(name,
		// cuisineId);
	}

	@GetMapping("/restaurants/first-by-name")
	public Optional<Restaurant> restaurantFirstByName(String name) {
		return restaurantRepository.findFirstRestaurantByNameContaining(name);
	}

	@GetMapping("/restaurants/top2-by-name")
	public List<Restaurant> restaurantsTop2ByName(String name) {
		return restaurantRepository.findTop2RestaurantByNameContaining(name);
	}

	@GetMapping("cuisines/exists")
	public boolean cuisineExists(String name) {
		return cuisineRepository.existsByName(name);
	}

	@GetMapping("restaurants/count-por-cuisine")
	public int restaurantsCountByCuisine(Long cuisineId) {
		return restaurantRepository.countByCuisineId(cuisineId);
	}

	@GetMapping("/restaurants/by-name-and-shipping-fee")
	public List<Restaurant> restaurantsByNameAndShippingFee(String name, BigDecimal intialShippingFee, BigDecimal finalShippingFee) {
		return restaurantRepository.find(name, intialShippingFee, finalShippingFee);
	}

	@GetMapping("/restaurants/with-free-shipping")
	public List<Restaurant> restaurantsWithFreeShipping(String name) {

		return restaurantRepository.findFreeShipping(name);
	}

	@GetMapping("/restaurants/first")
	public Optional<Restaurant> restaurantFirst() {
		return restaurantRepository.findFirst();
	}

	@GetMapping("/cuisines/first")
	public Optional<Cuisine> cuisineFirst() {
		return cuisineRepository.findFirst();
	}
}

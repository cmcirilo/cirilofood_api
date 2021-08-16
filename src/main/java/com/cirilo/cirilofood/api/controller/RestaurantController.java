package com.cirilo.cirilofood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cirilo.cirilofood.domain.exception.CuisineNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.model.CuisineModel;
import com.cirilo.cirilofood.api.model.RestaurantModel;
import com.cirilo.cirilofood.api.model.input.RestaurantInput;
import com.cirilo.cirilofood.core.validation.ValidationException;
import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.repository.RestaurantRepository;
import com.cirilo.cirilofood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private SmartValidator validator;

    @GetMapping
    public List<RestaurantModel> list() {
        return toCollectionModel(restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModel find(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);
        return toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel create(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = toDomainObject(restaurantInput);
            return toModel(restaurantService.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantModel update(@PathVariable Long restaurantId,
            @RequestBody @Valid RestaurantInput restaurantInput) {

        Restaurant restaurant = toDomainObject(restaurantInput);
        Restaurant currentRestaurant = restaurantService.find(restaurantId);
        BeanUtils.copyProperties(restaurant, currentRestaurant,
                "id", "formsPayment", "address", "createdDate", "products");

        try {
            return toModel(restaurantService.save(currentRestaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

//    @PatchMapping("/{restaurantId}")
//    public RestaurantModel parcialUpdate(@PathVariable Long restaurantId,
//            @RequestBody Map<String, Object> fields, HttpServletRequest request) {
//
//        Restaurant currentRestaurant = restaurantService.find(restaurantId);
//        merge(fields, currentRestaurant, request);
//        validate(currentRestaurant, "restaurant");
//
//        return update(restaurantId, currentRestaurant);
//    }
//
//    private void validate(Restaurant restaurant, String objectName) {
//        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);
//        validator.validate(restaurant, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException(bindingResult);
//        }
//    }
//
//    private void merge(Map<String, Object> originData, Restaurant destinationRestaurant, HttpServletRequest request) {
//
//        ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//
//            Restaurant originRestaurant = objectMapper.convertValue(originData, Restaurant.class);
//
//            originData.forEach((propertyName, propertyValue) -> {
//                Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
//                field.setAccessible(true);
//
//                Object novoValor = ReflectionUtils.getField(field, originRestaurant);
//
//                // System.out.println(propertyName + " = " + propertyValue + " = " + novoValor);
//
//                ReflectionUtils.setField(field, destinationRestaurant, novoValor);
//            });
//        } catch (IllegalArgumentException ex) {
//            Throwable rootCause = ExceptionUtils.getRootCause(ex);
//            throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, servletServerHttpRequest);
//        }
//
//    }

    private RestaurantModel toModel(Restaurant restaurant) {
        CuisineModel cuisineModel = new CuisineModel();
        cuisineModel.setId(restaurant.getCuisine().getId());
        cuisineModel.setName(restaurant.getCuisine().getName());

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(restaurant.getId());
        restaurantModel.setName(restaurant.getName());
        restaurantModel.setShippingFee(restaurant.getShippingFee());
        restaurantModel.setCuisine(cuisineModel);
        return restaurantModel;
    }

    private List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private Restaurant toDomainObject(RestaurantInput restaurantInput) {
        Restaurant restaurant = new Restaurant();

        restaurant.setName(restaurantInput.getName());
        restaurant.setShippingFee(restaurantInput.getShippingFee());

        Cuisine cuisine = new Cuisine();
        cuisine.setId(restaurantInput.getCuisine().getId());

        restaurant.setCuisine(cuisine);

        return restaurant;
    }
}
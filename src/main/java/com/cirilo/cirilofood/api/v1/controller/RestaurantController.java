package com.cirilo.cirilofood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import com.cirilo.cirilofood.core.security.CheckSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.v1.assembler.RestaurantBasicModelAssembler;
import com.cirilo.cirilofood.api.v1.assembler.RestaurantInputDisassembler;
import com.cirilo.cirilofood.api.v1.assembler.RestaurantModelAssembler;
import com.cirilo.cirilofood.api.v1.assembler.RestaurantOnlyNameModelAssembler;
import com.cirilo.cirilofood.api.v1.model.RestaurantBasicModel;
import com.cirilo.cirilofood.api.v1.model.RestaurantModel;
import com.cirilo.cirilofood.api.v1.model.RestaurantOnlyNameModel;
import com.cirilo.cirilofood.api.v1.model.input.RestaurantInput;
import com.cirilo.cirilofood.api.v1.openapi.controller.RestaurantControllerOpenApi;
import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.CityNotFoundException;
import com.cirilo.cirilofood.domain.exception.CuisineNotFoundException;
import com.cirilo.cirilofood.domain.exception.RestaurantNotFoundException;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.repository.RestaurantRepository;
import com.cirilo.cirilofood.domain.service.RestaurantService;

@RestController
@RequestMapping(path = "/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController implements RestaurantControllerOpenApi {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantModelAssembler restaurantModelAssembler;

    @Autowired
    private RestaurantInputDisassembler restaurantInputDisassembler;

    @Autowired
    private RestaurantBasicModelAssembler restaurantBasicModelAssembler;

    @Autowired
    private RestaurantOnlyNameModelAssembler restaurantOnlyNameModelAssembler;

    // @GetMapping
    // public MappingJacksonValue list(@RequestParam(required = false) String projection) {
    // List<Restaurant> restaurants = restaurantRepository.findAll();
    // List<RestaurantModel> restaurantsModel = restaurantModelAssembler.toCollectionModel(restaurants);
    //
    // MappingJacksonValue restaurantsWrapper = new MappingJacksonValue(restaurantsModel);
    //
    // restaurantsWrapper.setSerializationView(RestaurantView.Resume.class);
    //
    // if ("only-name".equals(projection)) {
    // restaurantsWrapper.setSerializationView(RestaurantView.OnlyName.class);
    // } else if ("complete".equals(projection)) {
    // restaurantsWrapper.setSerializationView(null);
    // }
    //
    // return restaurantsWrapper;
    // }

    // @JsonView(RestaurantView.Resume.class)
    @CheckSecurity.Restaurants.AllowList
    @GetMapping()
    public CollectionModel<RestaurantBasicModel> list() {
        return restaurantBasicModelAssembler
                .toCollectionModel(restaurantRepository.findAll());
    }

    @CheckSecurity.Restaurants.AllowList
    @GetMapping(params = "projection=complete")
    public CollectionModel<RestaurantModel> listComplete() {
        return restaurantModelAssembler.toCollectionModel(restaurantRepository.findAll());
    }

    // @JsonView(RestaurantView.OnlyName.class)
    @CheckSecurity.Restaurants.AllowList
    @GetMapping(params = "projection=only-name")
    public CollectionModel<RestaurantOnlyNameModel> listOnlyName() {
        return restaurantOnlyNameModelAssembler
                .toCollectionModel(restaurantRepository.findAll());
    }

    @CheckSecurity.Restaurants.AllowList
    @GetMapping("/{restaurantId}")
    public RestaurantModel find(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);
        return restaurantModelAssembler.toModel(restaurant);
    }

    @CheckSecurity.Restaurants.AllowManageRegistration
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel create(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);
            return restaurantModelAssembler.toModel(restaurantService.save(restaurant));
        } catch (CuisineNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @CheckSecurity.Restaurants.AllowManageRegistration
    @PutMapping("/{restaurantId}")
    public RestaurantModel update(@PathVariable Long restaurantId,
            @RequestBody @Valid RestaurantInput restaurantInput) {

        // Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);

        Restaurant currentRestaurant = restaurantService.find(restaurantId);
        restaurantInputDisassembler.copyToDomainObject(restaurantInput, currentRestaurant);

        // BeanUtils.copyProperties(restaurant, currentRestaurant,
        // "id", "formsPayment", "address", "createdDate", "products");

        try {
            return restaurantModelAssembler.toModel(restaurantService.save(currentRestaurant));
        } catch (CuisineNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @CheckSecurity.Restaurants.AllowManageRegistration
    @PutMapping("/{restaurantId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> activate(@PathVariable Long restaurantId) {
        restaurantService.activate(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.AllowManageRegistration
    @DeleteMapping("/{restaurantId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desactivate(@PathVariable Long restaurantId) {
        restaurantService.desactivate(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.AllowManageOperation
    @PutMapping("/{restaurantId}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> open(@PathVariable Long restaurantId) {
        restaurantService.open(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.AllowManageOperation
    @PutMapping("/{restaurantId}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> close(@PathVariable Long restaurantId) {
        restaurantService.close(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.AllowManageRegistration
    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activations(@RequestBody List<Long> restaurantIds) {
        try {
            restaurantService.activate(restaurantIds);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Restaurants.AllowManageRegistration
    @DeleteMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desactivations(@RequestBody List<Long> restaurantIds) {
        try {
            restaurantService.desactivate(restaurantIds);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}

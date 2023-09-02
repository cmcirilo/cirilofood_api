package com.cirilo.cirilofood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.cirilo.cirilofood.api.openapi.model.RestaurantBasicModelOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.assembler.RestaurantInputDisassembler;
import com.cirilo.cirilofood.api.assembler.RestaurantModelAssembler;
import com.cirilo.cirilofood.api.model.RestaurantModel;
import com.cirilo.cirilofood.api.model.input.RestaurantInput;
import com.cirilo.cirilofood.api.model.view.RestaurantView;
import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.CityNotFoundException;
import com.cirilo.cirilofood.domain.exception.CuisineNotFoundException;
import com.cirilo.cirilofood.domain.exception.RestaurantNotFoundException;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.repository.RestaurantRepository;
import com.cirilo.cirilofood.domain.service.RestaurantService;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantModelAssembler restaurantModelAssembler;

    @Autowired
    private RestaurantInputDisassembler restaurantInputDisassembler;

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

    @ApiOperation(value = "List restaurants", response = RestaurantBasicModelOpenApi.class)
    @ApiImplicitParams({
        @ApiImplicitParam(value = "Name of projection orders", allowableValues = "only-name",
                name = "projection", paramType = "query", type = "string")
    })
    @JsonView(RestaurantView.Resume.class)
    @GetMapping()
    public List<RestaurantModel> list() {
        return listComplete();
    }

    @GetMapping(params = "projection=complete")
    public List<RestaurantModel> listComplete() {
        return restaurantModelAssembler.toCollectionModel(restaurantRepository.findAll());
    }

    @ApiOperation(value = "List restaurants", hidden = true)
    @JsonView(RestaurantView.OnlyName.class)
    @GetMapping(params = "projection=only-name")
    public List<RestaurantModel> listOnlyName() {
        return listComplete();
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModel find(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);
        return restaurantModelAssembler.toModel(restaurant);
    }

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

    @PutMapping("/{restaurantId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long restaurantId) {
        restaurantService.activate(restaurantId);
    }

    @DeleteMapping("/{restaurantId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desactivate(@PathVariable Long restaurantId) {
        restaurantService.desactivate(restaurantId);
    }

    @PutMapping("/{restaurantId}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Long restaurantId) {
        restaurantService.open(restaurantId);
    }

    @PutMapping("/{restaurantId}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long restaurantId) {
        restaurantService.close(restaurantId);
    }

    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activations(@RequestBody List<Long> restaurantIds) {
        try {
            restaurantService.activate(restaurantIds);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

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

package com.cirilo.cirilofood.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.ResourceUriHelper;
import com.cirilo.cirilofood.api.assembler.CityInputDisassembler;
import com.cirilo.cirilofood.api.assembler.CityModelAssembler;
import com.cirilo.cirilofood.api.model.CityModel;
import com.cirilo.cirilofood.api.model.input.CityInput;
import com.cirilo.cirilofood.api.openapi.controller.CityControllerOpenApi;
import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.repository.CityRepository;
import com.cirilo.cirilofood.domain.service.CityService;

@RestController
@RequestMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityControllerOpenApi {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelAssembler cityModelAssembler;

    @Autowired
    private CityInputDisassembler cityInputDisassembler;

    @GetMapping
    public CollectionModel<CityModel> list() {
        List<City> cities = cityRepository.findAll();
        List<CityModel> citiesModel = cityModelAssembler.toCollectionModel(cities);

        citiesModel.forEach(cityModel -> {
            cityModel.add(linkTo(methodOn(CityController.class).find(cityModel.getId())).withSelfRel());
            cityModel.add(linkTo(methodOn(CityController.class).list()).withRel("cities"));
            cityModel.getState().add(linkTo(methodOn(StateController.class).find(cityModel.getState().getId())).withSelfRel());
        });

        CollectionModel<CityModel> citiesCollectionModel = new CollectionModel<>(citiesModel);
        citiesCollectionModel.add(linkTo(CityController.class).withSelfRel());

        return citiesCollectionModel;
    }

    @GetMapping("/{cityId}")
    public CityModel find(@PathVariable Long cityId) {
        City city = cityService.find(cityId);

        CityModel cityModel = cityModelAssembler.toModel(city);

        cityModel.add(linkTo(methodOn(CityController.class).find(cityModel.getId())).withSelfRel());
        cityModel.add(linkTo(methodOn(CityController.class).list()).withRel("cities"));
        cityModel.getState().add(linkTo(methodOn(StateController.class).find(cityModel.getState().getId())).withSelfRel());

        // cityModel.add(linkTo(CityController.class).slash(cityModel.getId()).withSelfRel());
        // cityModel.add(linkTo(CityController.class).slash(cityModel.getId()).withRel("cities"));
        //
        // cityModel.getState().add(linkTo(StateController.class).slash(cityModel.getState().getId()).withSelfRel());

        return cityModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel create(@RequestBody @Valid CityInput cityInput) {
        try {
            City city = cityInputDisassembler.toDomainObject(cityInput);
            city = cityService.save(city);

            CityModel cityModel = cityModelAssembler.toModel(city);

            ResourceUriHelper.addUriInResponseHeader(cityModel.getId());

            return cityModel;
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public CityModel update(@PathVariable Long cityId, @RequestBody @Valid CityInput cityInput) {

        try {
            City currentCity = cityService.find(cityId);
            cityInputDisassembler.copyToDomainObject(cityInput, currentCity);
            currentCity = cityService.save(currentCity);

            return cityModelAssembler.toModel(currentCity);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cityId) {
        cityService.delete(cityId);
    }

}

package com.cirilo.cirilofood.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.cirilo.cirilofood.api.assembler.CityInputDisassembler;
import com.cirilo.cirilofood.api.assembler.CityModelAssembler;
import com.cirilo.cirilofood.api.model.CityModel;
import com.cirilo.cirilofood.api.model.input.CityInput;
import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.repository.CityRepository;
import com.cirilo.cirilofood.domain.service.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelAssembler cityModelAssembler;

    @Autowired
    private CityInputDisassembler cityInputDisassembler;

    @GetMapping
    public List<CityModel> list() {
        List<City> cities = cityRepository.findAll();
        return cityModelAssembler.toCollectionModel(cities);
    }

    @GetMapping("/{cityId}")
    public CityModel find(@PathVariable Long cityId) {
        City city = cityService.find(cityId);
        return cityModelAssembler.toModel(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel create(@RequestBody @Valid CityInput cityInput) {
        try {
            City city = cityInputDisassembler.toDomainObject(cityInput);
            city = cityService.save(city);

            return cityModelAssembler.toModel(city);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public CityModel update(@PathVariable Long cityId,
            @RequestBody @Valid CityInput cityInput) {

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

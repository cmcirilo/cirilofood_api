package com.cirilo.cirilofood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cirilo.cirilofood.api.assembler.CityInputDisassembler;
import com.cirilo.cirilofood.api.assembler.CityModelAssembler;
import com.cirilo.cirilofood.api.model.CityModel;
import com.cirilo.cirilofood.api.model.input.CityInput;
import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.repository.CityRepository;
import com.cirilo.cirilofood.domain.service.CityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Cities")
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

    @ApiOperation("List Cities")
    @GetMapping
    public List<CityModel> list() {
        List<City> cities = cityRepository.findAll();
        return cityModelAssembler.toCollectionModel(cities);
    }

    @ApiOperation("Find City by Id")
    @GetMapping("/{cityId}")
    public CityModel find(@ApiParam(value = "City Id", example = "1") @PathVariable Long cityId) {
        City city = cityService.find(cityId);
        return cityModelAssembler.toModel(city);
    }

    @ApiOperation("Create City")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel create(@ApiParam(name = "body", value = "Representation of new city") @RequestBody @Valid CityInput cityInput) {
        try {
            City city = cityInputDisassembler.toDomainObject(cityInput);
            city = cityService.save(city);

            return cityModelAssembler.toModel(city);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @ApiOperation("Update City by Id")
    @PutMapping("/{cityId}")
    public CityModel update(@ApiParam(value = "City Id", example = "1") @PathVariable Long cityId,
                            @ApiParam(name = "body", value = "Representation of updated city") @RequestBody @Valid CityInput cityInput) {

        try {
            City currentCity = cityService.find(cityId);
            cityInputDisassembler.copyToDomainObject(cityInput, currentCity);
            currentCity = cityService.save(currentCity);

            return cityModelAssembler.toModel(currentCity);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @ApiOperation("Delete City by Id")
    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(value = "City Id", example = "1")@PathVariable Long cityId) {
        cityService.delete(cityId);
    }

}

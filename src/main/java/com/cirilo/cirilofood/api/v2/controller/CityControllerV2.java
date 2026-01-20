package com.cirilo.cirilofood.api.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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
import com.cirilo.cirilofood.api.v2.assembler.CityInputDisassemblerV2;
import com.cirilo.cirilofood.api.v2.assembler.CityModelAssemblerV2;
import com.cirilo.cirilofood.api.v2.model.CityModelV2;
import com.cirilo.cirilofood.api.v2.model.input.CityInputV2;
import com.cirilo.cirilofood.api.v2.openapi.controller.CityControllerV2OpenApi;
import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.repository.CityRepository;
import com.cirilo.cirilofood.domain.service.CityService;

@RestController
// @RequestMapping(path = "/cities", produces = CiriloMediaTypes.V2_APPLICATION_JSON_VALUE)
@RequestMapping(path = "/v2/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityControllerV2 implements CityControllerV2OpenApi {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelAssemblerV2 cityModelAssembler;

    @Autowired
    private CityInputDisassemblerV2 cityInputDisassembler;

    @GetMapping
    public CollectionModel<CityModelV2> list() {
        List<City> cities = cityRepository.findAll();
        return cityModelAssembler.toCollectionModel(cities);
    }

    @GetMapping("/{cityId}")
    public CityModelV2 find(@PathVariable Long cityId) {
        City city = cityService.find(cityId);
        return cityModelAssembler.toModel(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModelV2 create(@RequestBody @Valid CityInputV2 cityInput) {
        try {
            City city = cityInputDisassembler.toDomainObject(cityInput);
            city = cityService.save(city);

            CityModelV2 cityModel = cityModelAssembler.toModel(city);

            ResourceUriHelper.addUriInResponseHeader(cityModel.getIdCity());

            return cityModel;
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public CityModelV2 update(@PathVariable Long cityId, @RequestBody @Valid CityInputV2 cityInput) {

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

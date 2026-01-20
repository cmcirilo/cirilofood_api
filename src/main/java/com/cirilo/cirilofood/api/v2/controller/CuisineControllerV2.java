package com.cirilo.cirilofood.api.v2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.cirilo.cirilofood.api.v2.assembler.CuisineInputDisassemblerV2;
import com.cirilo.cirilofood.api.v2.assembler.CuisineModelAssemblerV2;
import com.cirilo.cirilofood.api.v2.model.CuisineModelV2;
import com.cirilo.cirilofood.api.v2.model.input.CuisineInputV2;
import com.cirilo.cirilofood.api.v2.openapi.controller.CuisineControllerV2OpenApi;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.repository.CuisineRepository;
import com.cirilo.cirilofood.domain.service.CuisineService;

@RestController
@RequestMapping(value = "/v2/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
public class CuisineControllerV2 implements CuisineControllerV2OpenApi {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private CuisineModelAssemblerV2 cuisineModelAssembler;

    @Autowired
    private CuisineInputDisassemblerV2 cuisineInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Cuisine> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<CuisineModelV2> list(Pageable pageable) {
        Page<Cuisine> cuisinesPage = cuisineRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(cuisinesPage, cuisineModelAssembler);
    }

    @GetMapping("/{cuisineId}")
    public CuisineModelV2 find(@PathVariable Long cuisineId) {
        Cuisine cuisine = cuisineService.find(cuisineId);
        return cuisineModelAssembler.toModel(cuisine);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineModelV2 create(@RequestBody @Valid CuisineInputV2 cuisineInput) {
        Cuisine cuisine = cuisineInputDisassembler.toDomainObject(cuisineInput);
        cuisine = cuisineService.save(cuisine);

        return cuisineModelAssembler.toModel(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public CuisineModelV2 update(@PathVariable Long cuisineId, @RequestBody @Valid CuisineInputV2 cuisineInput) {

        Cuisine curenteCuisine = cuisineService.find(cuisineId);
        cuisineInputDisassembler.copyToDomainObject(cuisineInput, curenteCuisine);
        curenteCuisine = cuisineService.save(curenteCuisine);

        return cuisineModelAssembler.toModel(curenteCuisine);
    }

    @DeleteMapping("/{cuisineid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cuisineid) {
        cuisineService.delete(cuisineid);
    }
}

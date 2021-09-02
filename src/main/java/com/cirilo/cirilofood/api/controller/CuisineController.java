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

import com.cirilo.cirilofood.api.assembler.CuisineInputDisassembler;
import com.cirilo.cirilofood.api.assembler.CuisineModelAssembler;
import com.cirilo.cirilofood.api.model.CuisineModel;
import com.cirilo.cirilofood.api.model.input.CuisineInput;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.repository.CuisineRepository;
import com.cirilo.cirilofood.domain.service.CuisineService;

@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private CuisineModelAssembler cuisineModelAssembler;

    @Autowired
    private CuisineInputDisassembler cuisineInputDisassembler;

    @GetMapping
    public List<CuisineModel> list() {
        List<Cuisine> cuisines = cuisineRepository.findAll();
        return cuisineModelAssembler.toCollectionModel(cuisines);
    }

    @GetMapping("/{cuisineId}")
    public CuisineModel find(@PathVariable Long cuisineId) {
        Cuisine cuisine = cuisineService.find(cuisineId);
        return cuisineModelAssembler.toModel(cuisine);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineModel create(@RequestBody @Valid CuisineInput cuisineInput) {
        Cuisine cuisine = cuisineInputDisassembler.toDomainObject(cuisineInput);
        cuisine = cuisineService.save(cuisine);

        return cuisineModelAssembler.toModel(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public CuisineModel atualizar(@PathVariable Long cuisineId, @RequestBody @Valid CuisineInput cuisineInput) {

        Cuisine curenteCuisine = cuisineService.find(cuisineId);
        cuisineInputDisassembler.copyToDomainObject(cuisineInput, curenteCuisine);
        curenteCuisine = cuisineService.save(curenteCuisine);

        return cuisineModelAssembler.toModel(curenteCuisine);
    }

    @DeleteMapping("/{cuisineid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cuisineid) {
        cuisineService.delete(cuisineid);
    }
}

package com.cirilo.cirilofood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.cirilo.cirilofood.domain.model.Cuisine;
import org.springframework.beans.BeanUtils;
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

import com.cirilo.cirilofood.domain.repository.CuisineRepository;
import com.cirilo.cirilofood.domain.service.CuisineService;

@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineService cuisineService;

    @GetMapping
    public List<Cuisine> list() {
        return cuisineRepository.findAll();
    }

    @GetMapping("/{cuisineId}")
    public Cuisine find(@PathVariable Long cuisineId) {
        return cuisineService.find(cuisineId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine create(@RequestBody @Valid Cuisine cuisine) {
        return cuisineService.save(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public Cuisine atualizar(@PathVariable Long cuisineId, @RequestBody @Valid Cuisine cuisine) {

        Cuisine cuisineAtual = cuisineService.find(cuisineId);
        BeanUtils.copyProperties(cuisine, cuisineAtual, "id");
        return cuisineService.save(cuisineAtual);
    }

    @DeleteMapping("/{cuisineid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cuisineid) {
        cuisineService.delete(cuisineid);
    }
}

package com.cirilo.cirilofood.api.v1.controller;

import javax.validation.Valid;

import com.cirilo.cirilofood.core.security.CheckSecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.cirilo.cirilofood.api.v1.assembler.CuisineInputDisassembler;
import com.cirilo.cirilofood.api.v1.assembler.CuisineModelAssembler;
import com.cirilo.cirilofood.api.v1.model.CuisineModel;
import com.cirilo.cirilofood.api.v1.model.input.CuisineInput;
import com.cirilo.cirilofood.api.v1.openapi.controller.CuisineControllerOpenApi;
import com.cirilo.cirilofood.domain.model.Cuisine;
import com.cirilo.cirilofood.domain.repository.CuisineRepository;
import com.cirilo.cirilofood.domain.service.CuisineService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/cuisines")
public class CuisineController implements CuisineControllerOpenApi {

//    private static final Logger logger = LoggerFactory.getLogger(CuisineController.class);

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private CuisineModelAssembler cuisineModelAssembler;

    @Autowired
    private CuisineInputDisassembler cuisineInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Cuisine> pagedResourcesAssembler;

//    @PreAuthorize("isAuthenticated()")
    @CheckSecurity.Cuisines.AllowList
    @GetMapping
    public PagedModel<CuisineModel> list(Pageable pageable) {
//        logger.info("List Cuisines with pages of {} records", pageable.getPageSize());
        log.info("List Cuisines with pages of {} records", pageable.getPageSize());

        Page<Cuisine> cuisinesPage = cuisineRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(cuisinesPage, cuisineModelAssembler);
    }

    @CheckSecurity.Cuisines.AllowList
    @GetMapping("/{cuisineId}")
    public CuisineModel find(@PathVariable Long cuisineId) {
        Cuisine cuisine = cuisineService.find(cuisineId);
        return cuisineModelAssembler.toModel(cuisine);
    }

    @CheckSecurity.Cuisines.AllowUpdate
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineModel create(@RequestBody @Valid CuisineInput cuisineInput) {
        Cuisine cuisine = cuisineInputDisassembler.toDomainObject(cuisineInput);
        cuisine = cuisineService.save(cuisine);

        return cuisineModelAssembler.toModel(cuisine);
    }

    @CheckSecurity.Cuisines.AllowUpdate
    @PutMapping("/{cuisineId}")
    public CuisineModel update(@PathVariable Long cuisineId, @RequestBody @Valid CuisineInput cuisineInput) {

        Cuisine curenteCuisine = cuisineService.find(cuisineId);
        cuisineInputDisassembler.copyToDomainObject(cuisineInput, curenteCuisine);
        curenteCuisine = cuisineService.save(curenteCuisine);

        return cuisineModelAssembler.toModel(curenteCuisine);
    }

//    @PreAuthorize("hasAuthority('UPDATE_CUISINES')")
    @CheckSecurity.Cuisines.AllowUpdate
    @DeleteMapping("/{cuisineid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cuisineid) {
        cuisineService.delete(cuisineid);
    }
}

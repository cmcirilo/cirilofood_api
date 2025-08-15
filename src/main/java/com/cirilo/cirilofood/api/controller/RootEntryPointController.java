package com.cirilo.cirilofood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.CiriloLinks;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private CiriloLinks ciriloLinks;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(ciriloLinks.linkToCuisines("cuisines"));
        rootEntryPointModel.add(ciriloLinks.linkToOrders("orders"));
        rootEntryPointModel.add(ciriloLinks.linkToRestaurants("restaurants"));
        rootEntryPointModel.add(ciriloLinks.linkToGroups("groups"));
        rootEntryPointModel.add(ciriloLinks.linkToUsers("users"));
        rootEntryPointModel.add(ciriloLinks.linkToPermissions("permissions"));
        rootEntryPointModel.add(ciriloLinks.linkToFormsPayment("forms-payment"));
        rootEntryPointModel.add(ciriloLinks.linkToStates("states"));
        rootEntryPointModel.add(ciriloLinks.linkToCities("cities"));
        rootEntryPointModel.add(ciriloLinks.linkToStatistics("statistics"));

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }

}

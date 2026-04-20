package com.cirilo.cirilofood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.core.security.CiriloSecurity;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private CiriloLinks ciriloLinks;

    @Autowired
    private CiriloSecurity ciriloSecurity;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        if (ciriloSecurity.allowListCuisines()) {
            rootEntryPointModel.add(ciriloLinks.linkToCuisines("cuisines"));
        }

        if (ciriloSecurity.allowListOrders()) {
            rootEntryPointModel.add(ciriloLinks.linkToOrders("orders"));
        }

        if (ciriloSecurity.allowListRestaurants()) {
            rootEntryPointModel.add(ciriloLinks.linkToRestaurants("restaurants"));
        }

        if (ciriloSecurity.allowListUsersGroupsPermissions()) {
            rootEntryPointModel.add(ciriloLinks.linkToGroups("groups"));
            rootEntryPointModel.add(ciriloLinks.linkToUsers("users"));
            rootEntryPointModel.add(ciriloLinks.linkToPermissions("permissions"));
        }

        if (ciriloSecurity.allowListFormsPayment()) {
            rootEntryPointModel.add(ciriloLinks.linkToFormsPayment("forms-payment"));
        }

        if (ciriloSecurity.allowListCities()) {
            rootEntryPointModel.add(ciriloLinks.linkToStates("states"));
        }

        if (ciriloSecurity.allowListStates()) {
            rootEntryPointModel.add(ciriloLinks.linkToCities("cities"));
        }

        if (ciriloSecurity.allowListStatistics()) {
            rootEntryPointModel.add(ciriloLinks.linkToStatistics("statistics"));
        }

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }

}

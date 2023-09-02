package com.cirilo.cirilofood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.openapi.controller.StatusOrderOpenApi;
import com.cirilo.cirilofood.domain.service.StatusOrderService;

@RestController
@RequestMapping(path = "/orders/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatusOrderController implements StatusOrderOpenApi {

    @Autowired
    private StatusOrderService statusOrderService;

    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String code) {
        statusOrderService.confirm(code);
    }

    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String code) {
        statusOrderService.cancel(code);
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delivery(@PathVariable String code) {
        statusOrderService.delivery(code);
    }
}

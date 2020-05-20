package com.cirilo.cirilofood.api.controller;

import java.util.List;

import com.cirilo.cirilofood.domain.model.Estado;
import com.cirilo.cirilofood.domain.model.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRespository;

    @GetMapping
    public List<Estado> listar() {
        return estadoRespository.listar();
    }
}

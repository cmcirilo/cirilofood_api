package com.cirilo.cirilofood.api.controller;

import java.util.List;

import com.cirilo.cirilofood.domain.exception.EntidadeEmUsoException;
import com.cirilo.cirilofood.domain.exception.EntidadeNaoEncontradaException;
import com.cirilo.cirilofood.domain.model.Cozinha;
import com.cirilo.cirilofood.domain.repository.CozinhaRepository;
import com.cirilo.cirilofood.domain.service.CadastroCozinhaService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cozinhas") // , produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping // (produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Cozinha> listar() {
        return cadastroCozinhaService.listar();
    }

    // @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    // public CozinhasXmlWrapper listarXml() {
    // return new CozinhasXmlWrapper(cozinhaRepository.listar());
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable final Long id) {
        try {
            Cozinha cozinha = cadastroCozinhaService.buscar(id);

            return ResponseEntity.ok(cozinha);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody final Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable final Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(id);

        if (cozinhaAtual != null) {
            // cozinhaAtual.setNome(cozinha.getNome());
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            cadastroCozinhaService.salvar(cozinhaAtual);

            return ResponseEntity.ok(cozinhaAtual);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable long id) {
        try {
            cadastroCozinhaService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}

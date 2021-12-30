package com.cirilo.cirilofood.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
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

import com.cirilo.cirilofood.api.assembler.FormPaymentInputDisassembler;
import com.cirilo.cirilofood.api.assembler.FormPaymentModelAssembler;
import com.cirilo.cirilofood.api.model.FormPaymentModel;
import com.cirilo.cirilofood.api.model.input.FormPaymentInput;
import com.cirilo.cirilofood.domain.model.FormPayment;
import com.cirilo.cirilofood.domain.repository.FormPaymentRepository;
import com.cirilo.cirilofood.domain.service.FormPaymentService;

@RestController
@RequestMapping("/forms-payment")
public class FormPaymentController {

    @Autowired
    private FormPaymentRepository formPaymentRepository;

    @Autowired
    private FormPaymentService formPaymentService;

    @Autowired
    private FormPaymentModelAssembler formPaymentModelAssembler;

    @Autowired
    private FormPaymentInputDisassembler formPaymentInputDisassembler;

    @GetMapping
    public ResponseEntity<List<FormPaymentModel>> list() {
        List<FormPayment> formsPaymment = formPaymentRepository.findAll();

        List<FormPaymentModel> formsPaymentModel =  formPaymentModelAssembler.toCollectioModel(formsPaymment);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formsPaymentModel);
    }

    @GetMapping("/{formPaymentId}")
    public FormPaymentModel find(@PathVariable Long formPaymentId) {
        FormPayment formPayment = formPaymentService.find(formPaymentId);
        return formPaymentModelAssembler.toModel(formPayment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormPaymentModel create(@RequestBody @Valid FormPaymentInput formPaymentInput) {
        FormPayment formPayment = formPaymentInputDisassembler.toDomainObject(formPaymentInput);

        formPayment = formPaymentService.save(formPayment);

        return formPaymentModelAssembler.toModel(formPayment);
    }

    @PutMapping("/{formPaymentId}")
    public FormPaymentModel udpate(@PathVariable Long formPaymentId,
            @RequestBody @Valid FormPaymentInput formPaymentInput) {
        FormPayment currentFormPayment = formPaymentService.find(formPaymentId);

        formPaymentInputDisassembler.copyToDomainObject(formPaymentInput, currentFormPayment);

        currentFormPayment = formPaymentService.save(currentFormPayment);

        return formPaymentModelAssembler.toModel(currentFormPayment);
    }

    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long formPaymentId) {
        formPaymentService.delete(formPaymentId);
    }

}

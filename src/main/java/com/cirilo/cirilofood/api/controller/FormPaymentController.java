package com.cirilo.cirilofood.api.controller;

import java.time.OffsetDateTime;
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
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

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
    public ResponseEntity<List<FormPaymentModel>> list(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime lastUpdatedDate = formPaymentRepository.getUpdatedDate();

        if (lastUpdatedDate != null) {
            eTag = String.valueOf(lastUpdatedDate.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormPayment> formsPaymment = formPaymentRepository.findAll();

        List<FormPaymentModel> formsPaymentModel = formPaymentModelAssembler.toCollectioModel(formsPaymment);

        return ResponseEntity.ok()
                // .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                // .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate()) //only local cache in browser
                // .cacheControl(CacheControl.noCache()) //cacheable but always validates eTags
                // .cacheControl(CacheControl.noStore()) //no cacheable
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic()) // allows public cache like reverse proxy
                .eTag(eTag)
                .body(formsPaymentModel);
    }

    @GetMapping("/{formPaymentId}")
    public ResponseEntity<FormPaymentModel> find(@PathVariable Long formPaymentId, ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime updatedDate = formPaymentRepository.getUpdatedDateById(formPaymentId);

        if (updatedDate != null) {
            eTag = String.valueOf(updatedDate.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        FormPayment formPayment = formPaymentService.find(formPaymentId);

        FormPaymentModel formPaymentModel = formPaymentModelAssembler.toModel(formPayment);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formPaymentModel);
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

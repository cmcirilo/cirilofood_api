package com.cirilo.cirilofood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.controller.FormPaymentController;
import com.cirilo.cirilofood.api.v1.model.FormPaymentModel;
import com.cirilo.cirilofood.domain.model.FormPayment;

@Component
public class FormPaymentModelAssembler
        extends RepresentationModelAssemblerSupport<FormPayment, FormPaymentModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public FormPaymentModelAssembler() {
        super(FormPaymentController.class, FormPaymentModel.class);
    }

    @Override
    public FormPaymentModel toModel(FormPayment formPayment) {
        FormPaymentModel formPaymentModel =
                createModelWithId(formPayment.getId(), formPayment);

        modelMapper.map(formPayment, formPaymentModel);

        formPaymentModel.add(ciriloLinks.linkToFormsPayment("formsPayment"));

        return formPaymentModel;
    }

    @Override
    public CollectionModel<FormPaymentModel> toCollectionModel(Iterable<? extends FormPayment> entities) {
        return super.toCollectionModel(entities)
                .add(ciriloLinks.linkToFormsPayment());
    }
}

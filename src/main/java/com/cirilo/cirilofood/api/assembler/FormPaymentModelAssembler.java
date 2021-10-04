package com.cirilo.cirilofood.api.assembler;

import com.cirilo.cirilofood.api.model.FormPaymentModel;
import com.cirilo.cirilofood.domain.model.FormPayment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormPaymentModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormPaymentModel toModel(FormPayment formPayment) {
        return modelMapper.map(formPayment, FormPaymentModel.class);
    }

    public List<FormPaymentModel> toCollectioModel(Collection<FormPayment> formsPayment) {
        return formsPayment.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

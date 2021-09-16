package com.cirilo.cirilofood.api.assembler;

import com.cirilo.cirilofood.api.model.input.FormPaymentInput;
import com.cirilo.cirilofood.domain.model.FormPayment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormPaymentInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormPayment toDomainObject(FormPaymentInput formPaymentInput){
        return modelMapper.map(formPaymentInput, FormPayment.class);
    }

    public void copyToDomainObject(FormPaymentInput formPaymentInput, FormPayment formPayment){
        modelMapper.map(formPaymentInput, formPayment);
    }
}

package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import com.cirilo.cirilofood.domain.exception.FormPaymentNotFoundException;
import com.cirilo.cirilofood.domain.model.FormPayment;
import com.cirilo.cirilofood.domain.repository.FormPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormPaymentService {

    private static final String MSG_FORM_PAYMENT_IN_USE = "Form Payment Id %d does not be removed, because is in use.";

    @Autowired
    private FormPaymentRepository formPaymentRepository;

    @Transactional
    public FormPayment save(FormPayment formPayment) {
        return formPaymentRepository.save(formPayment);
    }

    @Transactional
    public void delete(Long formPaymentId) {
        try {
            formPaymentRepository.deleteById(formPaymentId);
            formPaymentRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new FormPaymentNotFoundException(formPaymentId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException(String.format(MSG_FORM_PAYMENT_IN_USE, formPaymentId));
        }
    }

    public FormPayment find(Long formPaymentId) {
        return formPaymentRepository.findById(formPaymentId)
                .orElseThrow(() -> new FormPaymentNotFoundException(formPaymentId));
    }
}

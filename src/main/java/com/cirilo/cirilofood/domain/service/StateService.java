package com.cirilo.cirilofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cirilo.cirilofood.domain.exception.EntityInUseException;
import com.cirilo.cirilofood.domain.exception.StateNotFoundException;
import com.cirilo.cirilofood.domain.model.State;
import com.cirilo.cirilofood.domain.repository.StateRepository;

@Service
public class StateService {

    private static final String MSG_STATE_IN_USE = "State Id %d could not be removed bacause is in use.";

    @Autowired
    private StateRepository stateRepository;

    @Transactional
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Transactional
    public void delete(Long stateId) {
        try {
            stateRepository.deleteById(stateId);

        } catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException(stateId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_STATE_IN_USE, stateId));
        }
    }

    public State find(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new StateNotFoundException(stateId));
    }

}

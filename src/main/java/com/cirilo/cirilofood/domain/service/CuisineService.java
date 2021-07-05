package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.exception.CuisineNotFoundException;
import com.cirilo.cirilofood.domain.model.Cuisine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cirilo.cirilofood.domain.exception.EntityInUseException;
import com.cirilo.cirilofood.domain.repository.CuisineRepository;

@Service
public class CuisineService {

	private static final String MSG_CUISINE_IN_USE
			= "Cuisine Id %d does not be removed, because is in use.";

	@Autowired
	private CuisineRepository cuisineRepository;

	public Cuisine save(Cuisine cuisine) {
		return cuisineRepository.save(cuisine);
	}

	public void delete(Long cuisineId) {
		try {
			cuisineRepository.deleteById(cuisineId);

		} catch (EmptyResultDataAccessException e) {
			throw new CuisineNotFoundException(cuisineId);

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_CUISINE_IN_USE, cuisineId));
		}
	}

	public Cuisine find(Long cuisineId) {
		return cuisineRepository.findById(cuisineId)
				.orElseThrow(() -> new CuisineNotFoundException(cuisineId));
	}

}

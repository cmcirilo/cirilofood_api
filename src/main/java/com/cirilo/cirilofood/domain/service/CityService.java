package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.exception.CityNotFoudException;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cirilo.cirilofood.domain.exception.EntityInUseException;
import com.cirilo.cirilofood.domain.repository.CityRepository;

@Service
public class CityService {

	private static final String MSG_CITY_IN_USE
			= "City Id %d could not be removed because is in use.";

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateService stateService;

	public City save(City city) {
		Long stateId = city.getState().getId();

		State state = stateService.find(stateId);

		city.setState(state);

		return cityRepository.save(city);
	}

	public void delete(Long cityId) {
		try {
			cityRepository.deleteById(cityId);

		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoudException(cityId);

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_CITY_IN_USE, cityId));
		}
	}

	public City find(Long cityId) {
		return cityRepository.findById(cityId)
				.orElseThrow(() -> new CityNotFoudException(cityId));
	}
	
}

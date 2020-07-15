package com.cirilo.cirilofood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cirilo.cirilofood.domain.model.Estado;
import com.cirilo.cirilofood.domain.repository.EstadoRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> listar() {
		return manager.createQuery("from Estado", Estado.class)
				.getResultList();
	}
	
	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}
	
	@Transactional
	@Override
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}
	
	@Transactional
	@Override
	public void remover(Long id) {
		Estado estado = buscar(id);
		
		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(estado);
	}

}

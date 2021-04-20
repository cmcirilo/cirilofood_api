package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cirilo.cirilofood.domain.exception.EntidadeEmUsoException;
import com.cirilo.cirilofood.domain.exception.EntidadeNaoEncontradaException;
import com.cirilo.cirilofood.domain.model.Estado;
import com.cirilo.cirilofood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de estado com código %d";
	public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}

	public Estado buscar(Long estadoId){
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)));
	}
	
}
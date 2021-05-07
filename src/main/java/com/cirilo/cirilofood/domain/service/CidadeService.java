package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.exception.CidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cirilo.cirilofood.domain.exception.EntidadeEmUsoException;
import com.cirilo.cirilofood.domain.model.Cidade;
import com.cirilo.cirilofood.domain.model.Estado;
import com.cirilo.cirilofood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	private static final String MSG_CIDADE_EM_USO
			= "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoService estadoService;

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();

		Estado estado = estadoService.buscar(estadoId);

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);

		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

	public Cidade buscar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}
	
}

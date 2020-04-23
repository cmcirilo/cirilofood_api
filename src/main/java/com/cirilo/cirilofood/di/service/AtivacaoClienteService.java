package com.cirilo.cirilofood.di.service;

import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.di.modelo.Cliente;
import com.cirilo.cirilofood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	private Notificador notificador;
	
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
		
		System.out.println("AtivacaoClienteService: " + notificador);
	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
	
}

package com.cirilo.cirilofood.di.notificacao;

import com.cirilo.cirilofood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}
package com.cirilo.cirilofood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cirilo.cirilofood.di.modelo.Cliente;
import com.cirilo.cirilofood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {

	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
		
		System.out.println("MeuPrimeiroController: " + ativacaoClienteService);
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		Cliente joao = new Cliente("Cirilo","cirilo@cirilo.com","3413434");
		
		ativacaoClienteService.ativar(joao);
		
		return "Hello";
	}
	
}

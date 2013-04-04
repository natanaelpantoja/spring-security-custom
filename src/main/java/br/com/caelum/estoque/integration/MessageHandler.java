package br.com.caelum.estoque.integration;

import org.springframework.integration.annotation.MessageEndpoint;

import br.com.caelum.estoque.model.Movimentacao;

@MessageEndpoint
public class MessageHandler {

	
	public Movimentacao handler(Movimentacao movimentacao) {
		System.out.println("Roteando movimentação: "+movimentacao);
		return movimentacao;
	}
}

package br.com.caelum.estoque.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.caelum.estoque.TipoMovimentacao;
import br.com.caelum.estoque.dao.ProdutoDAO;
import br.com.caelum.estoque.model.Movimentacao;
import br.com.caelum.estoque.model.Produto;


@Service
public class GeradorDeMovimentacao {

	@Autowired
	@Qualifier("produtoHibernateDAO")
	private ProdutoDAO produtoDAO;
	
	
	public Movimentacao geraMovimentacao(Produto produto){
		Movimentacao mov = new Movimentacao();
		mov.setData(Calendar.getInstance());
		mov.setProduto(produto);
		
		Integer quantidadeAtual = produtoDAO.estoqueAtual(produto);
		if(produto.getQuantidade() > quantidadeAtual){
			mov.setTipo(TipoMovimentacao.ENTRADA);
		}else{
			mov.setTipo(TipoMovimentacao.SAIDA);
		}
		
		mov.setQuantidade(Math.abs(produto.getQuantidade() - quantidadeAtual));
		return mov;
	}
}

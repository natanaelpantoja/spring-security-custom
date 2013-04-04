package br.com.caelum.estoque.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.estoque.dao.MovimentacaoDAO;
import br.com.caelum.estoque.dao.ProdutoDAO;
import br.com.caelum.estoque.model.Movimentacao;
import br.com.caelum.estoque.model.Produto;


@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	
	@Autowired
	@Qualifier("produtoHibernateDAO")
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private GeradorDeMovimentacao geradorMovimentacao;
	
	@Autowired
	private MovimentacaoDAO  movimentacaoDAO;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		return "produtos/index";
	}
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView lista() {
		List<Produto> produtos = produtoDAO.listar();
		
		
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject(produtos);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/mostra/{id}", method=RequestMethod.GET)
	public ModelAndView mostrar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("produtos/mostra");
		modelAndView.addObject(produtoDAO.buscarPorId(id));
		return modelAndView;
	}
		
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String form() {
		return "produtos/form";
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public String salvar(@Valid Produto produto, BindingResult result) {
		if(result.hasErrors()) {
			return "produtos/form";
		}
		produtoDAO.salvar(produto);
		return "redirect:/produtos/listar.html";
	}
	
	@RequestMapping(value="/alterar", method=RequestMethod.POST)
	public String alterar( @Valid Produto produto){
		Movimentacao movimentacao = geradorMovimentacao.geraMovimentacao(produto);
		movimentacaoDAO.salvar(movimentacao);
		produtoDAO.alterar(produto);
		return "redirect:/produtos/listar";
	}
}

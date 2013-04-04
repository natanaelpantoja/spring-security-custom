package br.com.caelum.estoque.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.estoque.model.Produto;

@Repository
@Primary
@Transactional
public class ProdutoHibernateDAO implements ProdutoDAO {
	
	
	private SessionFactory factory;
	
	@Autowired
	public ProdutoHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	@Transactional
	public void salvar(Produto produto) {
		factory.getCurrentSession().save(produto);
	}

	@Override
	public void alterar(Produto produto) {
		factory.getCurrentSession().update(produto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listar() {
		List<Produto>  produtos = factory.getCurrentSession().createQuery("from Produto").list();
		return produtos;
	}

	@Override
	public Produto buscarPorId(Long id) {
		return (Produto) factory.getCurrentSession().get(Produto.class, id);
	}

	@Override
	public Integer estoqueAtual(Produto produto) {
		Query query = factory.getCurrentSession().createQuery("select quantidade from Produto where id =:pid");
		query.setParameter("pid", produto.getId());
		
		return (Integer) query.uniqueResult();
	}

}

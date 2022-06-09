package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {
	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = this.em.merge(categoria);
		this.em.remove(categoria);
	}
	
	public Categoria buscarPorId(Long id) {
		return em.find(Categoria.class, id);
	}
	
	public List<Categoria> buscarTodos() {
		String jpql = "SELECT p FROM Categoria p";	
		return em.createQuery(jpql, Categoria.class).getResultList();
	}
	
	public List<Categoria> buscarPorNomeNamed(String nome) {
		String jpql = "SELECT p FROM Categoira p WHERE p.nome = :nome";
		return em.createQuery(jpql, Categoria.class)
				.setParameter("nome", nome)
				.getResultList();
	}	
	
	public List<Categoria> buscarPorNomePosition(String nome) {
		String jpql = "SELECT p FROM Categoira p WHERE p.nome = ?1";
		return em.createQuery(jpql, Categoria.class)
				.setParameter(1, nome)
				.getResultList();
	}	
	
}

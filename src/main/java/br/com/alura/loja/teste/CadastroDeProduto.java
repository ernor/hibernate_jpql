package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		
		System.out.println(produto.getPreco());
		
		List<Produto> todos = produtoDao.buscarTodos();
	
		todos.forEach(p -> System.out.println(p.getNome()));
		
		List<Produto> todosNome = produtoDao.buscarPorNomeNamed("Xiaomi Redfmi");
		
		todosNome.forEach(p -> System.out.println(p.getNome()));
		
		List<Produto> buscaCategoria = produtoDao.buscarPorNomeCategoira("CELULARES");
		
		buscaCategoria.forEach(p2 -> System.out.println(p2.getCategoria().getNome()));
	
		BigDecimal precoProduto = produtoDao.buscarPrecoProdutoComNome("Xiaomi Redfmi");
		System.out.println("Preço Produto: " + precoProduto);
	
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		
		Produto celular = new Produto("Xiaomi Redfmi", "Muito legal", new BigDecimal("800"), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}

}

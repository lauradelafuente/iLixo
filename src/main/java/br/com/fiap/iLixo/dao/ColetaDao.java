package br.com.fiap.iLixo.dao;

import java.util.List;

import br.com.fiap.iLixo.model.Coleta;
import jakarta.persistence.EntityManager;

public class ColetaDao {

	private EntityManager em;
	
	//constutor, fornece o em para cada contrução de coleta DAO
	public ColetaDao(EntityManager em) {
		this.em = em;
	}
	
	//faz a persistencia do coleta no DB
	public void salvar(Coleta coleta) {
		em.persist(coleta);
	}
	
	//faz a atualização do coleta no DB através do merge
	public void atualizar(Coleta coleta) {
		em.merge(coleta);
	}
	
	public void excluir(Coleta coleta) {
		//localiza o registro através do id 
		Coleta coletaExcluir = em.find(Coleta.class, coleta.getId());
		//remove o objeto encontrado no DB
		em.remove(coletaExcluir);
	}
	
	public Coleta consultarColetaPorId(Coleta coleta){
	    return em.find(Coleta.class, coleta.getId()); // Utilizando o ID da coleta passada como argumento
	}
	
	public List<Coleta> listarTodosColetas(){
		//sql - SELECT * FROM tb_coleta ORDER BY nome ASC (ascendente/alfabetica)
		//jpql
		String consulta = "SELECT c FROM Coleta c ORDER BY nome ASC";
		//em cria uma consulta no banco utilizando a instrução jpql passada e o resultado é transformado em uma lista de coletas 
		return em.createQuery(consulta).getResultList();
	}
	
}

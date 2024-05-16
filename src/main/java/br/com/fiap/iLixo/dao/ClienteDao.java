package br.com.fiap.iLixo.dao;

import java.util.List;

import br.com.fiap.iLixo.model.Cliente;
import jakarta.persistence.EntityManager;

//classe responsável por fazer a persistência de um objeto Cliente no DB através do EntityManager
public class ClienteDao {

	private EntityManager em;
	
	//constutor, fornece o em para cada contrução de cliente DAO
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	//faz a persistencia do cliente no DB
	public void salvar(Cliente cliente) {
		em.persist(cliente);
	}
	
	//faz a atualização do cliente no DB através do merge
	public void atualizar(Cliente cliente) {
		em.merge(cliente);
	}
	
	public void excluir(Cliente cliente) {
		//localiza o registro através do id 
		Cliente clienteExcluir = em.find(Cliente.class, cliente.getId());
		//remove o objeto encontrado no DB
		em.remove(clienteExcluir);
	}
	
	public void consultarClientePorId(Long id){
		//localiza o registro através do id
		Cliente clienteConsulta = em.find(Cliente.class, id);
		
		if(clienteConsulta == null){
			System.out.println("Cliente não encontrado");
		} else {
			System.out.println("-------------------------");
			//chama o tostring para impressão no console
			System.out.println(clienteConsulta.toString());
			System.out.println("-------------------------");

		}
	}
	
	public List<Cliente> listarTodosClientes(){
		//sql - SELECT * FROM tb_cliente ORDER BY nome ASC (ascendente/alfabetica)
		//jpql
		String consulta = "SELECT c FROM Cliente c ORDER BY nome ASC";
		//em cria uma consulta no banco utilizando a instrução jpql passada e o resultado é transformado em uma lista de clientes 
		return em.createQuery(consulta).getResultList();
	}
	
	public List<Cliente> listarClientePorEmail(String emailProcurado){
		String consulta = "SELECT c FROM Cliente c WHERE email = :email";
		
		return em.createQuery(consulta, Cliente.class)
				//nome do parametro no jpql / valor que vai substituir o parametro  
				.setParameter("email", emailProcurado)
				.getResultList();
	}
}

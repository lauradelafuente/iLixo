package br.com.fiap.iLixo;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.iLixo.dao.ClienteDao;
import br.com.fiap.iLixo.dao.ColetaDao;
import br.com.fiap.iLixo.dao.Conexao;
import br.com.fiap.iLixo.model.Cliente;
import br.com.fiap.iLixo.model.Coleta;
import jakarta.persistence.EntityManager;

public class iLixoApp {

	public static void main(String[] args) {

		//faz a interacao com o DB
		EntityManager em = Conexao.getEntitiyManager();
		
		//cadastrar(em);
		//atualizar(em);
		//excluir(em);
		//consultarClientePorId(em);
		//listarTodosClientes(em);
		//listarClientePorEmail(em);
		consultarColetaPorId(em);
	}
	
	private static void consultarColetaPorId(EntityManager em) {
	    ColetaDao coletaDao = new ColetaDao(em);
	    Coleta coletaBuscada = new Coleta();
	    coletaBuscada.setId(21L); // Definindo o ID da coleta que você quer buscar
	    Coleta coletaEncontrada = coletaDao.consultarColetaPorId(coletaBuscada); // Passando a coleta com o ID definido

	    System.out.println("ID da Coleta: " + coletaEncontrada.getId()); // Imprimindo o ID da coleta
	    System.out.println(coletaEncontrada.getClientes());
	}
	
	public static void listarClientePorEmail(EntityManager em) {
		//cria uma instancia do DAO 
		ClienteDao clienteDao = new ClienteDao(em);
		
		//lista os contatos 
		List<Cliente> clientes = clienteDao.listarClientePorEmail("laura@email.com.br");
		//cria a variavel cliente que recebe a cada volta do loop cada cliente em registro  
		for(Cliente cliente : clientes) {
			System.out.println("---------------------");
			System.out.println(cliente.toString());
			System.out.println("---------------------");
		}
	}
	
	public static void listarTodosClientes(EntityManager em) {
		//cria uma instancia do DAO 
		ClienteDao clienteDao = new ClienteDao(em);
		
		//lista os contatos 
		List<Cliente> clientes = clienteDao.listarTodosClientes();
		//cria a variavel cliente que recebe a cada volta do loop cada cliente em registro  
		for(Cliente cliente : clientes) {
			System.out.println("---------------------");
			System.out.println(cliente.toString());
			System.out.println("---------------------");
		}
	}
	
	public static void cadastrar(EntityManager em) {
		Coleta coleta = new Coleta();
		coleta.setId(21L);
		//coleta.setCepInicial("09863100");
		//coleta.setCepFinal("09863950");
		//coleta.setDataColeta(LocalDate.of(2024, 5, 8));
		
		ColetaDao coletaDao = new ColetaDao(em);
		
		//abre a transacao (pois altera dados no banco)
		em.getTransaction().begin();
		//coletaDao.salvar(coleta);
		
		Cliente cliente = new Cliente();
		cliente.setNome("Mel");
		cliente.setCpf("385057178856");
		cliente.setCep("09863200");
		cliente.setEndereco("Rua Espacial, 515");
		cliente.setTelefone(1194577862L);
		cliente.setEmail("mel@email.com.br");
		cliente.setColeta(coleta);
		
		//cria uma instancia do DAO 
		ClienteDao clienteDao = new ClienteDao(em);
		
		//manda o cliente para o DB 
		clienteDao.salvar(cliente);
		//finaliza a transacao após persistido, determina o registro no DB
		em.getTransaction().commit();
	}
	
	public static void atualizar(EntityManager em) {
		Cliente cliente = new Cliente();
		cliente.setId(21L);
		cliente.setNome("José José");
		cliente.setCpf("123456");
		cliente.setCep("09861600");
		cliente.setEndereco("Rua das Ruas, 18");
		cliente.setTelefone(11912334566L);
		cliente.setEmail("joséjosé@fiap.com.br");	
		
		//cria uma instancia do DAO 
		ClienteDao clienteDao = new ClienteDao(em);
		
		//abre a transacao
		em.getTransaction().begin();
		//manda o cliente para o DB e atualiza 
		clienteDao.atualizar(cliente);
		//finaliza a transacao após persistido, determina o registro no DB
		em.getTransaction().commit();
	}
	
	public static void excluir(EntityManager em) {
		Cliente cliente = new Cliente();
		cliente.setId(2L);	
		
		//cria uma instancia do DAO 
		ClienteDao clienteDao = new ClienteDao(em);
		
		//abre a transacao
		em.getTransaction().begin();
		//exclui o registro no DB
		clienteDao.excluir(cliente);
		//finaliza a transacao após persistido, determina o registro no DB
		em.getTransaction().commit();
	}
	
	public static void consultarClientePorId(EntityManager em) {
		//cria uma instancia do DAO 
		ClienteDao clienteDao = new ClienteDao(em);
		
		//abre a transacao
		em.getTransaction().begin();
		//faz a consulta por id
		clienteDao.consultarClientePorId(46L);
		//finaliza a transacao após persistido, determina o registro no DB
		em.getTransaction().commit();
	}
}
	
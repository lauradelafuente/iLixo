package br.com.fiap.iLixo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//classe responsável por entregar um objeto EntityManager para as outras classes que forem consumir o DB
public class Conexao {

	//atributo estatico/constante EMF inicializada do tipo EntityManager
	public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("iLixo");
	
	//método estático get que retorna um objeto EM, 
	public static EntityManager getEntitiyManager() {
		return EMF.createEntityManager();
	}
}

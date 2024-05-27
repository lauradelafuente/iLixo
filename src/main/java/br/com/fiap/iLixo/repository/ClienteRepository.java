package br.com.fiap.iLixo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.iLixo.model.Cliente;

//classe que implementa todos os métodos de persitencia
public interface ClienteRepository extends JpaRepository<Cliente, Long>{ //o que vai mandar (qual tipo de objeto) / campo identificador do objeto 

	@Query("SELECT c FROM Cliente c WHERE c.nome = :nome") //c se refere ao objeto cliente
	public List<Cliente> findByNome(@Param("nome") String nome); //parametro a ser substituido na consulta jpql
	
	public Optional<Cliente> findByEmail(String email);
}

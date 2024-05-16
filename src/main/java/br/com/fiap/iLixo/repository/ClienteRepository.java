package br.com.fiap.iLixo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.iLixo.model.Cliente;

//classe que implementa todos os métodos de persitencia
public interface ClienteRepository extends JpaRepository<Cliente, Long>{ //o que vai mandar (qual tipo de objeto) / campo identificador do objeto 

	//optional pois trata npe
	public Optional<Cliente> findByNome(String nome);
}

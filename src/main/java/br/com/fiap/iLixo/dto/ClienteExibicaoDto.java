package br.com.fiap.iLixo.dto;

import br.com.fiap.iLixo.model.Cliente;

//envia os atributos que o consumidor da api vai exibir
public record ClienteExibicaoDto(
		Long id,
		String nome,
		String cpf,
		String cep,
		String endereco,
		long telefone,
		String email
		){
	//this faz referencia ao dto, que vai receber as informações do objeto cliente
	public ClienteExibicaoDto(Cliente cliente) {
		this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getCep(), cliente.getEndereco(), cliente.getTelefone(), cliente.getEmail());
	}
}

package br.com.fiap.iLixo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteCadastroDto(		
		Long id,
		@NotBlank(message = "O nome é obrigatório") //não pode ser vazio ou nulo
		String nome,
		String cpf,
		@NotBlank(message = "O cep é obrigatório")
		String cep,
		String endereco,
		long telefone,
		@NotBlank(message = "O email é obrigatório")
		@Email(message = "O email é invalido")
		String email,
		@NotBlank(message = "A senha é obrigatória")
		@Size(min = 6, max = 15, message = "A senha deve conter entre 6 e 15 caracteres")
		String senha) {

}

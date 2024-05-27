package br.com.fiap.iLixo.dto;

import br.com.fiap.iLixo.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(
		Long usuarioId,
		@NotBlank(message = "O nome é obrigatório")
		String nome,
		@NotBlank(message = "O email é obrigatório")
		@Email(message = "O email é invalido")
		String email,
		@NotBlank(message = "A senha é obrigatória")
		@Size(min = 6, max = 15, message = "A senha deve conter entre 6 e 15 caracteres")
		String senha,
		UsuarioRole role) {
}

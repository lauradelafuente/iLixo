package br.com.fiap.iLixo.dto;

import br.com.fiap.iLixo.model.Usuario;
import br.com.fiap.iLixo.model.UsuarioRole;

public record UsuarioExibicaoDto( 
	Long usuarioId,
	String nome,
	String email,
	UsuarioRole role
	) {
	public UsuarioExibicaoDto(Usuario usuario) {
		this(usuario.getUsuarioId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
	}
}

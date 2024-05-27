package br.com.fiap.iLixo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.fiap.iLixo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	UserDetails findByEmail(String email);
}

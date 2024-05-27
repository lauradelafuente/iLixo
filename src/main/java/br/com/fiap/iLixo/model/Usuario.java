package br.com.fiap.iLixo.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_USUARIO_SEQ")
	@SequenceGenerator(name = "TB_USUARIO_SEQ", sequenceName = "TB_USUARIO_SEQ", allocationSize = 1)
	@Column(name = "usuario_id")
	private Long usuarioId;
	private String nome;
	private String email;
	private String senha;
	@Enumerated(EnumType.STRING) //pega a string no enum
	private UsuarioRole role;
	
	//gerencia o permissionamento (permissoes garantidas)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		if(this.role == UsuarioRole.ADMIN) {
			//se o user for admin retorna a lista de grantedAuthority (pode fazer todos os papeis), se não, apenas permissões de usuario 
			//passa o nome do papel que será criado para o objeto 
			return List.of(
					new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			return List.of(
					new SimpleGrantedAuthority("ROLE_USER"));
		}
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	//verifica se a conta nao esta expirada 
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public UsuarioRole getRole() {
		return role;
	}
	public void setRole(UsuarioRole role) {
		this.role = role;
	}
}

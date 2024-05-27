package br.com.fiap.iLixo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.iLixo.repository.UsuarioRepository;

//serviço de autenticação, será invocado sempre que um usuario se autenticar
//implementa UserDetailsService para que o Spring reconheça que a classe é responsavel pela autorizaçao
@Service
public class AuthorizationService implements UserDetailsService{

	//responsavel por fazer a consulta no db
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//método utilizado para que o spring secutiry faça a consulta do usuario que esta tentando se autenticar
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return usuarioRepository.findByEmail(username);
	}	
}

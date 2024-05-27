package br.com.fiap.iLixo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.iLixo.config.security.TokenService;
import br.com.fiap.iLixo.dto.LoginDto;
import br.com.fiap.iLixo.dto.TokenDto;
import br.com.fiap.iLixo.dto.UsuarioCadastroDto;
import br.com.fiap.iLixo.dto.UsuarioExibicaoDto;
import br.com.fiap.iLixo.model.Usuario;
import br.com.fiap.iLixo.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	//gerenciador de autenticacao
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) {
		//representa o usuairo e senha
		//cria um objeto UsernamePasswordAuthenticationToken que tem a senha e o usuario que vai se autenticar
		UsernamePasswordAuthenticationToken usernamePassword = 
				new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha());
		//objeto que gerencia a autenticacao recebendo os dados do usuario que esta se autenticando 
		Authentication auth = authenticationManager.authenticate(usernamePassword);
		//gera um token de autenticação para o usuário autenticado
		String token = tokenService.gerarToken((Usuario) auth.getPrincipal());
		
		return ResponseEntity.ok(new TokenDto(token));	
	}
	

	@PostMapping("/registrer") 
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
		UsuarioExibicaoDto usuarioSalvo = null;
		usuarioSalvo = service.gravar(usuarioCadastroDto);
		return usuarioSalvo;
	}
}

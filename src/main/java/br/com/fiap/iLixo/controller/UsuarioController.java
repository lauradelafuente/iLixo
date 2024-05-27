package br.com.fiap.iLixo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.iLixo.dto.UsuarioCadastroDto;
import br.com.fiap.iLixo.dto.UsuarioExibicaoDto;
import br.com.fiap.iLixo.model.Usuario;
import br.com.fiap.iLixo.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired 
	private UsuarioService service;
	
	@PostMapping("/gravar")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioExibicaoDto gravar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
		return service.gravar(usuarioCadastroDto);
		
	}
	
	@GetMapping("/listar")
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> listarTodosOsUsuarios() {
		return service.listarTodosOsUsuarios();
	}
	
	@GetMapping("/buscarPorId/{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioExibicaoDto buscarPorId(@PathVariable Long usuarioId) {
		return service.buscarPorId(usuarioId);
	}
	
	@DeleteMapping("/deletar/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long usuarioId) {
		service.excluir(usuarioId);
	}
	
	@PutMapping("/atualizar/{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioExibicaoDto atualizar(@RequestBody UsuarioCadastroDto usuarioCadastroDto, @PathVariable Long usuarioId) {
		return service.atualizar(usuarioCadastroDto);
	}
	
}

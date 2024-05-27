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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.iLixo.dto.ClienteCadastroDto;
import br.com.fiap.iLixo.dto.ClienteExibicaoDto;
import br.com.fiap.iLixo.model.Cliente;
import br.com.fiap.iLixo.service.ClienteService;
import jakarta.validation.Valid;

//classe que recebe a requisição vinda do cliente
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired 
	private ClienteService service;
	
	@PostMapping("/gravar")
	//devolve 201 - objeto criado
	@ResponseStatus(HttpStatus.CREATED)
	//@1 - quando a requisição for enviada, o contato será gravado e o spring pega o objeto no body da req e converte o json (que vai receber uma string) em um objeto persistido no banco
	//@2 - faz a validação do dto
	public ClienteExibicaoDto gravar(@RequestBody @Valid ClienteCadastroDto clienteCadastroDto) {
		//chama o método gravar da service que utiliza o cliente recebido e utiliza o repository para fazer a gravação 	
		return service.gravar(clienteCadastroDto);
		
	}
	
	@GetMapping("/listar")
	//devolve 200 - consulta com sucesso
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listarTodosOsClientes() {
		return service.listarTodosOsClientes();
	}
	
	@GetMapping("/buscarPorId/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteExibicaoDto buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@DeleteMapping("/deletar/{id}")
	//devolve 204 - objeto excluido
	@ResponseStatus(HttpStatus.NO_CONTENT)
	//@PathVariable - o valor é passado para a variável {id} na busca
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
	@PutMapping("/atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteExibicaoDto atualizar(@PathVariable Long id, @RequestBody ClienteCadastroDto clienteCadastroDto) {
		return service.atualizar(clienteCadastroDto);
	}
	
	@GetMapping("/buscarPorNome/nome/{nome}") 
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> buscarClientePeloNome(@PathVariable String nome) {
		return service.buscarPeloNome(nome);
	}
	
	@GetMapping(value = "/buscarPorNome", params = "nome")
	public List<Cliente> buscarClientePorNome(@RequestParam String nome) {
		return service.buscarPeloNome(nome);
	}
	
	//cliente/buscarPorEmail?email=laura@email.com
	@GetMapping(value = "/buscarPorEmail", params = "email")
	public ClienteExibicaoDto buscarClientePeloEmail(@RequestParam String email) {
		return service.buscarClientePorEmail(email);
	}
}

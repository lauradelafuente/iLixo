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

import br.com.fiap.iLixo.model.Cliente;
import br.com.fiap.iLixo.service.ClienteService;

//classe que recebe a requisição vinda do cliente
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired 
	private ClienteService service;
	
	@PostMapping("/gravar")
	//devolve 201 - objeto criado
	@ResponseStatus(HttpStatus.CREATED)
	//@ - quando a requisição for enviada, o contato será gravado e o spring pega o objeto no body da req e converte o json (que vai receber uma string) em um objeto persistido no banco
	public Cliente gravar(@RequestBody Cliente cliente) {
		//chama o método gravar da service que utiliza o cliente recebido e utiliza o repository para fazer a gravação 	
		return service.gravar(cliente);
		
	}
	
	@GetMapping("/listar")
	//devolve 200 - consulta com sucesso
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listarTodosOsClientes() {
		return service.listarTodosOsClientes();
	}
	
	@DeleteMapping("/deletar/{id}")
	//devolve 204 - objeto excluido
	@ResponseStatus(HttpStatus.NO_CONTENT)
	//@PathVariable - o valor é passado para a variável {id} na busca
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
	@PutMapping("/atualizar")
	@ResponseStatus(HttpStatus.OK)
	public Cliente atualizar(Cliente cliente) {
		return service.atualizar(cliente);
	}
	
	@GetMapping("/buscarPorNome/{nome}") 
	@ResponseStatus(HttpStatus.OK)
	public Cliente buscarClientePeloNome(@PathVariable String nome) {
		return service.buscarPeloNome(nome);
	}
}

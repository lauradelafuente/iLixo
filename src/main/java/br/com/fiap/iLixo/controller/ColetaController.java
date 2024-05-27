package br.com.fiap.iLixo.controller;

import java.time.LocalDate;
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

import br.com.fiap.iLixo.model.Coleta;
import br.com.fiap.iLixo.service.ColetaService;


//classe que recebe a requisição vinda do coleta
@RestController
@RequestMapping("/coletas")
public class ColetaController {

	@Autowired 
	private ColetaService service;
	
	@PostMapping("/gravar")
	//devolve 201 - objeto criado
	@ResponseStatus(HttpStatus.CREATED)
	//@ - quando a requisição for enviada, o contato será gravado e o spring pega o objeto no body da req e converte o json (que vai receber uma string) em um objeto persistido no banco
	public Coleta gravar(@RequestBody Coleta coleta) {
		//chama o método gravar da service que utiliza o coleta recebido e utiliza o repository para fazer a gravação 	
		return service.gravar(coleta);
		
	}
	
	@GetMapping("/listar")
	//devolve 200 - consulta com sucesso
	@ResponseStatus(HttpStatus.OK)
	public List<Coleta> listarTodasAsColetas() {
		return service.listarTodasAsColetas();
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
	public Coleta atualizar(@PathVariable Long id, @RequestBody Coleta coleta) {
		return service.atualizar(coleta);
	}
	
	//coletas/mostrarPorData/2024-05-19/2024-05-28
	@GetMapping("/mostrarPorData/{dataInicial}/{dataFinal}") 
	@ResponseStatus(HttpStatus.OK)
	public List<Coleta> mostrarColetas(@PathVariable LocalDate dataInicial, @PathVariable LocalDate dataFinal) {
		return service.mostarColetas(dataInicial, dataFinal);
	}
	
	//coletas/mostrarPorData?dataInicial=2024-05-19&dataFinal=2024-05-28
	@GetMapping(value = "/mostrarPorData", params = {"dataInicial","dataFinal"}) 
	@ResponseStatus(HttpStatus.OK)
	public List<Coleta> mostrarColetasPorData(@RequestParam LocalDate dataInicial, @RequestParam LocalDate dataFinal) {
		return service.mostarColetas(dataInicial, dataFinal);
	}
}

package br.com.fiap.iLixo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.iLixo.model.Cliente;
import br.com.fiap.iLixo.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	//salva e retorna o objeto
	public Cliente gravar(Cliente cliente) {
		return clienteRepository.save(cliente); //usando a implementação save da interface clienteRepository (extends JpaRepository)
	}
	
	public Cliente buscarPorId(Long id) {
		//objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		
		//verifica se existe algo no obj clienteOptional
		if(clienteOptional.isPresent()) {
			return clienteOptional.get();
		} else {
			//lança a excessão em tempo de execução
			throw new RuntimeException("Cliente não encontrado");
		}
	}
	
	public List<Cliente> listarTodosOsClientes() {
		//retorna a lista de clientes do banco
		return clienteRepository.findAll();
	}
	
	
	public void excluir(Long id) {
		//objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		
		if(clienteOptional.isPresent()) {
			//chama o delete do repository que passa o objeto a ser excluido, apaga o cliente que virá do cliente optoinal da busca no banco 
			clienteRepository.delete(clienteOptional.get());;
		} else {
			//lança a excessão em tempo de execução
			throw new RuntimeException("Cliente não encontrado");
		}
	}
	
	public Cliente atualizar(Cliente cliente) {
		// objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getId());

		if (clienteOptional.isPresent()) {
			// chama o save e salva o cliente - se o objeto não tiver id, vai criar um novo objeto / se o objeto é passado com id, reconhece que vai atualizar 
			return clienteRepository.save(cliente);
		} else {
			// lança a excessão em tempo de execução
			throw new RuntimeException("Cliente não encontrado");
		}
	}
	
	public Cliente buscarPeloNome(String nome) {
		Optional<Cliente> clienteOptional = clienteRepository.findByNome(nome);	
		if(clienteOptional.isPresent()) {
			return clienteOptional.get();
		} else {
			throw new RuntimeException("Cliente não encontrado");
		}		
	}
}

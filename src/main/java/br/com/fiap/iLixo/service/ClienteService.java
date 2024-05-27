package br.com.fiap.iLixo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.iLixo.dto.ClienteCadastroDto;
import br.com.fiap.iLixo.dto.ClienteExibicaoDto;
import br.com.fiap.iLixo.exception.ClienteNaoEncontradoException;
import br.com.fiap.iLixo.model.Cliente;
import br.com.fiap.iLixo.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	//salva e retorna o objeto
	public ClienteExibicaoDto gravar(ClienteCadastroDto clienteCadastroDto) {
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(clienteCadastroDto, cliente); //copia as propriedades, de fonte (dto )/ pra destino (cliente)
		return new ClienteExibicaoDto(clienteRepository.save(cliente)); //usando a implementação save da interface clienteRepository (extends JpaRepository)
	}
	
	public ClienteExibicaoDto buscarPorId(Long id) {
		//objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		
		//passa o contato vindo do repositorio e constroi a exibicao DTO
		if(clienteOptional.isPresent()) {
			return new ClienteExibicaoDto(clienteOptional.get());
		} else {
			//lança a excecao em tempo de execução
			throw new ClienteNaoEncontradoException("Cliente não encontrado");
		}
	}
	
	public ClienteExibicaoDto buscarClientePorEmail(String email) {
		Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);
		if(clienteOptional.isPresent()) {
			return new ClienteExibicaoDto(clienteOptional.get()); 
		} else {
			throw new ClienteNaoEncontradoException("Cliente não encontrado");
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
	
	public ClienteExibicaoDto atualizar(ClienteCadastroDto clienteDto) {
		// objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Cliente> clienteOptional = clienteRepository.findById(clienteDto.id());

		if (clienteOptional.isPresent()) {
			Cliente cliente = new Cliente();
			BeanUtils.copyProperties(clienteDto, cliente);
			// chama o save e salva o cliente - se o objeto não tiver id, vai criar um novo objeto / se o objeto é passado com id, reconhece que vai atualizar 
			return new ClienteExibicaoDto(clienteRepository.save(cliente));
		} else {
			// lança a excessão em tempo de execução
			throw new RuntimeException("Cliente não encontrado");
		}
	}
	
    public List<Cliente> buscarPeloNome(String nome) {
        List<Cliente> clientes = clienteRepository.findByNome(nome);
        if (clientes.isEmpty()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        return clientes;
    }
}

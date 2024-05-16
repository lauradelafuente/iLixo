package br.com.fiap.iLixo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.iLixo.model.Coleta;
import br.com.fiap.iLixo.repository.ColetaRepository;

@Service
public class ColetaService {

	@Autowired
	ColetaRepository coletaRepository;

	// salva e retorna o objeto
	public Coleta gravar(Coleta coleta) {
		return coletaRepository.save(coleta); // usando a implementação save da interface coletaRepository (extends
												// JpaRepository)
	}

	public Coleta buscarPorId(Long id) {
		// objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Coleta> coletaOptional = coletaRepository.findById(id);

		// verifica se existe algo no obj clienteOptional
		if (coletaOptional.isPresent()) {
			return coletaOptional.get();
		} else {
			// lança a excessão em tempo de execução
			throw new RuntimeException("Coleta não encontrada");
		}
	}

	public List<Coleta> listarTodasAsColetas() {
		// retorna a lista de coletas do banco
		return coletaRepository.findAll();
	}

	public void excluir(Long id) {
		// objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Coleta> coletaOptional = coletaRepository.findById(id);

		if (coletaOptional.isPresent()) {
			// chama o delete do repository que passa o objeto a ser excluido, apaga o
			// coleta que virá do coleta optoinal da busca no banco
			coletaRepository.delete(coletaOptional.get());
			;
		} else {
			// lança a excessão em tempo de execução
			throw new RuntimeException("Coleta não encontrada");
		}
	}

	// chama o findBtColetaBeetwen para mostrar as coletas entre a data inicial e
	// final
	public List<Coleta> mostarColetas(LocalDate dataInicial, LocalDate dataFinal) {
		return coletaRepository.findByDataColetaBetween(dataInicial, dataFinal);
	}

	public Coleta atualizar(Coleta coleta) {
		// objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Coleta> coletaOptional = coletaRepository.findById(coleta.getId());

		if (coletaOptional.isPresent()) {
			// chama o save e salva a coleta - se o objeto não tiver id, vai criar um novo objeto / se o objeto é passado com id, reconhece que vai atualizar 
			return coletaRepository.save(coleta);
		} else {
			// lança a excessão em tempo de execução
			throw new RuntimeException("Coleta não encontrada");
		}
	}
}

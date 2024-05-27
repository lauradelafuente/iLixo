package br.com.fiap.iLixo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.iLixo.dto.UsuarioCadastroDto;
import br.com.fiap.iLixo.dto.UsuarioExibicaoDto;
import br.com.fiap.iLixo.exception.UsuarioNaoEncontradoException;
import br.com.fiap.iLixo.model.Usuario;
import br.com.fiap.iLixo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//salva e retorna o objeto
	public UsuarioExibicaoDto gravar(UsuarioCadastroDto usuarioCadastroDto) {
		//bcrypt criptograda a senha passada no metodo encode
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());
		
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(usuarioCadastroDto, usuario); //copia as propriedades, de fonte (dto)/ pra destino (usuario)
		usuario.setSenha(senhaCriptografada);
		 
		return new UsuarioExibicaoDto(usuarioRepository.save(usuario)); //usando a implementação save da interface usuarioRepository (extends JpaRepository)
		
	}
	
	public UsuarioExibicaoDto buscarPorId(Long usuarioId) {
		//objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
		
		//passa o contato vindo do repositorio e constroi a exibicao DTO
		if(usuarioOptional.isPresent()) {
			return new UsuarioExibicaoDto(usuarioOptional.get());
		} else {
			//lança a excecao em tempo de execução
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
		}
	}
	
	/*public UsuarioExibicaoDto buscarUsuarioPorEmail(String email) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		if(usuarioOptional.isPresent()) {
			return new UsuarioExibicaoDto(usuarioOptional.get()); 
		} else {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
		}
	}*/
	
	public List<Usuario> listarTodosOsUsuarios() {
		//retorna a lista de usuarios do banco
		return usuarioRepository.findAll();
	}
	
	
	public void excluir(Long usuarioId) {
		//objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
		
		if(usuarioOptional.isPresent()) {
			//chama o delete do repository que passa o objeto a ser excluido, apaga o usuario que virá do usuario optoinal da busca no banco 
			usuarioRepository.delete(usuarioOptional.get());;
		} else {
			//lança a excessão em tempo de execução
			throw new RuntimeException("Usuario não encontrado");
		}
	}
	
	public UsuarioExibicaoDto atualizar(UsuarioCadastroDto usuarioDto) {
		// objeto optional (lida com npe) que recebe o retorno do findById (optional)
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioDto.usuarioId());

		if (usuarioOptional.isPresent()) {
			Usuario usuario = new Usuario();
			BeanUtils.copyProperties(usuarioDto, usuario);
			// chama o save e salva o usuario - se o objeto não tiver usuarioId, vai criar um novo objeto / se o objeto é passado com usuarioId, reconhece que vai atualizar 
			return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
		} else {
			// lança a excessão em tempo de execução
			throw new RuntimeException("Usuario não encontrado");
		}
	}
}

package br.com.fiap.iLixo.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{

	public UsuarioNaoEncontradoException(String message) {
		//super chama o construtor de runtimeexception
		super(message);
	}

}

package br.com.fiap.iLixo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontradoException extends RuntimeException{

	public ClienteNaoEncontradoException(String message) {
		//super chama o construtor de runtimeexception
		super(message);
	}
}

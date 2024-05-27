package br.com.fiap.iLixo.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//faz o tratamento de excecoes da aplicacao
@RestControllerAdvice
public class ApplicationExceptionHandler {

	//map - chave e valor
	//função que retorna um mapa com chave e valor, quem chamar a função deve passar a exception MANVE
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class) //classe de escecao que representa o erro que ta tratando
	public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro) {
		Map<String, String> mapaDeErro = new HashMap<String, String>();
		List<FieldError> campos = erro.getBindingResult().getFieldErrors();//classe do bean validation que lista todos os campos de erro
		//a cada interacao pega um field error e guarda em campo
		for(FieldError campo : campos) {
			mapaDeErro.put(campo.getField(), campo.getDefaultMessage()); //coloca os valores no mapa, filtra a exibicao do erro em nome dos campos e descricao
		}
		return mapaDeErro;
	}
}

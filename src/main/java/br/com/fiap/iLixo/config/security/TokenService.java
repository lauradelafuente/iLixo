package br.com.fiap.iLixo.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.iLixo.model.Usuario;

@Service
public class TokenService {
	
	@Value("${minha.chave.secreta}")
	private String palavraSecreta;
	
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
	
	public String gerarToken(Usuario usuario) {
		try {
			//algoritimo HMAC256 aplica um calculo matematico de comparação dos dados do usuario e chave secreta que faz a geração do token
			Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);
			String token = JWT
					.create()
					//emissor do token
					.withIssuer("cliente")
					//identifica o usuario
					.withSubject(usuario.getEmail())
					//tempo de exiracao
					.withExpiresAt(gerarDataDeExpiracao())
					//aplica a criptografia (assina o token)
					.sign(algorithm);
			return token;
		//se gerar o token o usuario está valido, se cair em alguma exceção vai retornar o token vazio
		} catch(JWTCreationException erro){
			logger.error("Erro ao validar token", erro);
			throw new RuntimeException("Não foi possivel gerar o token!");
		}
	}
	
	public String validarToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);
			//require decifra o token usando o algoritimo
			return JWT.require(algorithm)
					.withIssuer("cliente")
					//constrói o algoritimo
					.build()
					//verifica o token passado pelo usario e ve se é igual ao token criado novamente
					.verify(token)
					//pega o email do usuario
					.getSubject();
		} catch(JWTVerificationException erro) {
			logger.error("Erro ao verificar token");
			return "";
		}
	}
	
	public Instant gerarDataDeExpiracao() {
		//o tempo de expiracao do token é agora acrescido de duas horas de acordo com o fuso horario
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}


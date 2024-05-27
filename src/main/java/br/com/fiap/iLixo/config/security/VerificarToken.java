package br.com.fiap.iLixo.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.iLixo.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//herda a classe para garantir que a verificação seja feita apenas uma vez
public class VerificarToken extends OncePerRequestFilter{

	@Autowired
	private TokenService tokenService;
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Override
	//filtro que vai ser chamado antes no SecurityConfig para saber se há o token, se houver o token é extraido para depois pegar os dados do usuario 
	protected void doFilterInternal(
			//dados da req usuario (header, json)
			HttpServletRequest request,
			//dados da resp do usuario
			HttpServletResponse response,
			//filtros passados
			FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//na req vem o authorizationHeader
		String authorizationHeader = request.getHeader("Authorization");
		String token = "";
		
		if(authorizationHeader == null) {
			token = null;
		} else {
			//recorta a String "Bearer" da resposta do token
			token = authorizationHeader.replace("Bearer ", "").trim();
			String login = tokenService.validarToken(token);
			UserDetails usuario = usuarioRepository.findByEmail(login);
			
			
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(
							//objeto principal
							usuario,
							//credenciais (não tem)
							null,
							//coleção de permissões
							usuario.getAuthorities());
			//pega o contexto da aplicação e seta a autenticação, agora a aplicação sabe quem está solicitando algo
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		//faz o filtro de acordo com a requisição e a resposta
		filterChain.doFilter(request, response);
	} 
}

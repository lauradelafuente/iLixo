package br.com.fiap.iLixo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//spring security reconhece que é uma classe de conf
@Configuration
//habilita a configuração de segurança web em uma aplicação spring - intercepta requisições HTTP
@EnableWebSecurity
//classe responsavel pela configuração do filtro de segurança aplicado na app
public class SecurityConfig {
	
	@Autowired
	private VerificarToken verificarToken;

    @Bean
    //metodo que retorna um objeto do tipo SecurityFilterChain que configura a segurança da aplicação, proteje as requisicoes HTTP
    public SecurityFilterChain filtrarCadeiaDeSeguranca(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        		//desabilita a proteção contra ataques CSRF
                .csrf(csrf -> csrf.disable())
                //habilitando a autenticação stateless apontando a politica da cricao de sessao
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                		.requestMatchers(HttpMethod.POST, "/auth/registrer").permitAll()
                		.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                		.requestMatchers(HttpMethod.GET, "/coletas/listar").hasAnyRole("ADMIN", "USER")
                		.requestMatchers(HttpMethod.POST, "/coletas/gravar").hasAnyRole("ADMIN")
                		.requestMatchers(HttpMethod.PUT, "/coletas/atualizar").hasAnyAuthority("ADMIN")
                		.requestMatchers(HttpMethod.PUT, "/coletas/deletar").hasAnyAuthority("ADMIN")
                		.requestMatchers(HttpMethod.GET, "/usuario/listar").hasAnyRole("ADMIN", "USER")
                		.requestMatchers(HttpMethod.POST, "/usuario/gravar").hasAnyRole("ADMIN", "USER")
                		.requestMatchers(HttpMethod.PUT, "/usuario/atualizar").hasAnyAuthority("ADMIN", "USER")
                		.requestMatchers(HttpMethod.PUT, "/usuario/deletar").hasAnyAuthority("ADMIN")
                		.requestMatchers(HttpMethod.GET, "/cliente/listar").hasAnyRole("ADMIN")
                		.requestMatchers(HttpMethod.POST, "/cliente/gravar").hasAnyRole("ADMIN")
                		.requestMatchers(HttpMethod.PUT, "/cliente/atualizar").hasAnyAuthority("ADMIN")
                		.requestMatchers(HttpMethod.PUT, "/cliente/deletar").hasAnyAuthority("ADMIN")
                		.anyRequest()
                		.authenticated())
                //antes de qualquer filtro, deve ser verificado o token e o usuario. joga os dados do usuario no contexto da aplicação 
                .addFilterBefore(verificarToken, UsernamePasswordAuthenticationFilter.class)
                //finaliza a criacao do objeto
                .build();
    }
    
    @Bean
    //gerenciador de autenticação 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();    
    }
    
    @Bean
    //passwordEncoder - codificador de senhas, retorna o objeto BCryptPasswordEncoder que criptografa a senha
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

}

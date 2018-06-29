package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ufc.br.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImplementacao userDetailsImplementacao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/inicio").permitAll() // Permito todo mundo acessar /inicio
		//.antMatchers("/usuario/formulario").hasRole("USER") //Somente usuario com papel "USER" acessa /usuario/formulario
		.antMatchers("/usuario/formulario").permitAll()
		//.antMatchers("/usuario/salvar").hasAnyRole("USER","ADMIN") // Usuario com papel "USER" ou "ADMIN" acessa /usuario/salvar
		.antMatchers("/usuario/salvar").permitAll()
		
		.antMatchers("/produto/galeria").permitAll()
		.antMatchers("/produto/listar").hasAnyRole("ADMIN")
		.antMatchers("/gerencia").hasAnyRole("ADMIN")
		.antMatchers("/produto/formulario").hasAnyRole("ADMIN")
		.antMatchers("/produto/salvar").hasAnyRole("ADMIN")
		
		.antMatchers("/usuario/listar").hasAnyRole("ADMIN") // admin permissao
		//.antMatchers("/produto/formulario").hasAnyRole("ADMIN")
		
		//CARRINHO 
		//.antMatchers("/carrinho").hasAnyRole("USER")
		
		.anyRequest().authenticated() // o resto precisa está autenticado
		
		.and()
		.formLogin()
		.loginPage("/usuario/logar") // Esse é o controller que chama nosso formulario
		.permitAll() //permitir acesso para essa url "entrar"
		
		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		.and()
		.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         .logoutSuccessUrl("/login");
		/*
		.and()
		.logout()
		.logoutSuccessUrl("/usuario/logar?logout") // logout sucesso
		.permitAll();
		
		.and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login");
        
        */
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**","/images/**"); // ignora e permite uri's com esses arquivos
	}

}

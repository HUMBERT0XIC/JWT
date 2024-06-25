package com.mx.Catalago.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/api/items/**").authenticated() // Proteger las URLs que
																								// requieran
																								// autenticación
				.anyRequest().permitAll() // Permitir acceso libre a otras URLs
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Configurar sesión
																									// sin estado
	}

	// Bean para configurar el administrador de autenticación
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

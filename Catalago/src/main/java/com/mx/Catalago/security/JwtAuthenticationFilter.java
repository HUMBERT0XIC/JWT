package com.mx.Catalago.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Obtener el token del header Authorization
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		String username = null;
		String token = null;

		// Validar el formato del token
		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			try {
				username = jwtService.getUsernameFromToken(token);
			} catch (Exception e) {
				logger.error("Error al obtener el usuario con el token", e);
			}
		}

		// Validar el usuario y establecer la autenticación en el contexto de Spring Security
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			if (jwtService.validateToken(token)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
						null, Collections.emptyList());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}
}

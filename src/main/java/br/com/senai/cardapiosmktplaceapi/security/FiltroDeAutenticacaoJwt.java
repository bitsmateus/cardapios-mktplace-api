package br.com.senai.cardapiosmktplaceapi.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.senai.cardapiosmktplaceapi.service.impl.CredencialDeAcessoServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroDeAutenticacaoJwt extends OncePerRequestFilter {

	@Autowired
	private GerenciadorDeTokenJwt gerenciadorDeToken;

	@Autowired
	private CredencialDeAcessoServiceImpl service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authReader = request.getHeader("Authorization");
		String token = null;
		String login = null;

		boolean isHeaderAuthorizationPresent = authReader != null && authReader.startsWith("Bearer ");
		if (isHeaderAuthorizationPresent) {
			token = authReader.substring(7);
			login = gerenciadorDeToken.extrairLoginDo(token);
		}

		boolean isNovoLogin = login != null && SecurityContextHolder.getContext().getAuthentication() == null;
		if (isNovoLogin) {
			UserDetails credencial = service.loadUserByUsername(login);
			if (gerenciadorDeToken.isValido(token, credencial)) {
				UsernamePasswordAuthenticationToken tokenAutenticado = new UsernamePasswordAuthenticationToken(
						credencial, null, credencial.getAuthorities());
				tokenAutenticado.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(tokenAutenticado);
			}
		}
		filterChain.doFilter(request, response);

	}

}

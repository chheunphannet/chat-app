package com.chatapi.chat_app.Security;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFiltter extends UsernamePasswordAuthenticationFilter{
	private final String secretKey;
	private final AuthenticationManager authenticationManager;
	
	public JwtFiltter(String secretKey, AuthenticationManager authenticationManager) {
		this.secretKey = secretKey;
		this.authenticationManager = authenticationManager;
		super.setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			LoginRequest loginValue = mapper.readValue(request.getInputStream(), LoginRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(loginValue.getUsername(),
					loginValue.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
		} catch (IOException e) {
			throw new BadCredentialsException("Invalid login request format", e);
		}

	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		if(secretKey == null || secretKey.trim().isEmpty()) {
			throw new IllegalStateException("JWT secret key is not configured. Please set jwt.secret in application.properties");
		}
		
		String token = Jwts.builder()
				.subject(authResult.getName())
				.issuedAt(new Date())
				.claim("authorities", authResult.getAuthorities())
				.expiration(Date.from(Instant.now().plus(1, ChronoUnit.MINUTES)))
				.issuer("chatapp")
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
		response.setHeader("Authorization","Bearer " + token);
	}
	
}

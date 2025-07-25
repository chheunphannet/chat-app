package com.chatapi.chat_app.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(c -> c.disable())
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()
				//.anyRequest().authenticated()
				)
				.formLogin(Customizer.withDefaults()) // Enables form-based login
				.httpBasic(Customizer.withDefaults());
				//.oauth2Login(Customizer.withDefaults());
		return http.build();
	}

}

package com.chatapi.chat_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapi.chat_app.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<?> userLogin(@AuthenticationPrincipal OAuth2User auth2User,
				@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
		
		userService.register(auth2User.getAttributes(), client);
		
		return ResponseEntity.ok("hello " + (String) auth2User.getAttributes().get("given_name"));
	}
	
	
}

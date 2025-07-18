package com.chatapi.chat_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	@GetMapping("/")
	public ResponseEntity<?> userLogin(@AuthenticationPrincipal OAuth2User auth2User) {
		return ResponseEntity.ok(auth2User.getAttributes());
	}
	
	
}

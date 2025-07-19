package com.chatapi.chat_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatapi.chat_app.Dto.UserDto;
import com.chatapi.chat_app.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
//	@GetMapping("/")
//	public ResponseEntity<?> userLogin(@AuthenticationPrincipal OAuth2User auth2User) {
//		return ResponseEntity.ok(auth2User.getAttributes());
//	}
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> userLogin(@RequestBody UserDto dto) {
		return ResponseEntity.ok(userService.register(dto));
	}
	
	@GetMapping("/getByUsername") 
	public ResponseEntity<?> getByUsername(@RequestParam String username ) {
		return ResponseEntity.ok(userService.loadUserByUserName(username));
	}
	
	
	
}

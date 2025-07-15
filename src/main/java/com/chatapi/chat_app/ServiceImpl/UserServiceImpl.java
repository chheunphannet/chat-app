package com.chatapi.chat_app.ServiceImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import com.chatapi.chat_app.Entity.User;
import com.chatapi.chat_app.Service.UserService;
import com.chatapi.chat_app.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements UserService{
	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public void register(Map<String, Object> getAttributes, OAuth2AuthorizedClient client ) {
		User user = new User();
		user.setEmail((String) getAttributes.get("email"));
		user.setFirstName((String) getAttributes.get("given_name"));
		user.setLastName((String) getAttributes.get("family_name"));
		user.setAccessToken(client.getAccessToken().getTokenValue());
		user.setUsername("@" + user.getFullName().toLowerCase());
		user.setTokenExpiresAt(toLocaDateTime((Instant)getAttributes.get("exp")));
		userRepository.save(user);
	}
	
	private LocalDateTime toLocaDateTime(Instant exp) { 
		 return exp.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}

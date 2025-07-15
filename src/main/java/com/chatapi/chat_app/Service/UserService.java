package com.chatapi.chat_app.Service;

import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

public interface UserService {
	public void register(Map<String, Object> getAttributes, OAuth2AuthorizedClient client );
}

package com.chatapi.chat_app.Oauth2;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDetailsCustom implements OAuth2User, UserDetails {
	
	private Long id;
	private String name;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	private Map<String, Object> attributes;
	
	public UserDetailsCustom(Long id, String username, List<GrantedAuthority> authorities, String password) {
		this.id = id;
		this.username = username;
		this.authorities = authorities;
		this.password = password;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}


}

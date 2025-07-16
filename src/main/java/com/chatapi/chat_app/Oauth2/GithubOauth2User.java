package com.chatapi.chat_app.Oauth2;

import java.util.Map;

public class GithubOauth2User extends Oauth2UserDetail{

	public GithubOauth2User(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getFirstName() {
		return (String) attributes.get("name");
	}

	@Override
	public String getGmail() {
		return (String) attributes.get("id");
	}

	@Override
	public String getLastName() {
		return null;
	}

	@Override
	public String getFullName() {
		return (String) attributes.get("login");
	}

}

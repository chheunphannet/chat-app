package com.chatapi.chat_app.Oauth2;

import java.util.Map;

public class FacebookOauth2User extends Oauth2UserDetail{

	public FacebookOauth2User(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getFirstName() {
		return null;
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
		return (String) attributes.get("name");
	}

}

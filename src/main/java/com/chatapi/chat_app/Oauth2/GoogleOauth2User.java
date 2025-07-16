package com.chatapi.chat_app.Oauth2;

import java.util.Map;

public class GoogleOauth2User extends Oauth2UserDetail{

	public GoogleOauth2User(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getFirstName() {
		return (String) attributes.get("given_name");
	}

	@Override
	public String getGmail() {
		return (String) attributes.get("gmail");
	}

	@Override
	public String getLastName() {
		return (String) attributes.get("family_name");
	}
  
	@Override
	public String getFullName() {
		return (String) attributes.get("given_name") +" "+ (String) attributes.get("family_name");
	}

}

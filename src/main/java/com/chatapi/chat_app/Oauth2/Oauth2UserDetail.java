package com.chatapi.chat_app.Oauth2;

import java.util.Map;

public abstract class Oauth2UserDetail {
	protected Map<String, Object> attributes;

	public abstract String getFirstName();

	public abstract String getGmail();

	public abstract String getLastName();

	public abstract String getFullName();

	public Oauth2UserDetail(Map<String, Object> attributes) {
		this.attributes = attributes;

	}

}

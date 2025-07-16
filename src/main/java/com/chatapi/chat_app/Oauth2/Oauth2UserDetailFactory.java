package com.chatapi.chat_app.Oauth2;

import com.chatapi.chat_app.Enum.Provider;
import com.chatapi.chat_app.Exception.BaseException;

import java.util.Map;

public class Oauth2UserDetailFactory {
	public static Oauth2UserDetail getOauth2UserDetail(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equals(Provider.facebook.name())) {
			return new FacebookOauth2User(attributes);
		} else if (registrationId.equals(Provider.google.name())) {
			return new GoogleOauth2User(attributes);
		} else if (registrationId.equals(Provider.github.name())) {
			return new GithubOauth2User(attributes);
		} else {
			throw new BaseException("400", "sorry! loing with r" + registrationId + " not support");
		}
	}
}

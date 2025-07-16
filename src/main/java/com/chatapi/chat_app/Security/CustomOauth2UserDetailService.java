package com.chatapi.chat_app.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.chatapi.chat_app.Entity.User;
import com.chatapi.chat_app.Exception.BaseException;
import com.chatapi.chat_app.Oauth2.Oauth2UserDetail;
import com.chatapi.chat_app.Oauth2.Oauth2UserDetailFactory;
import com.chatapi.chat_app.repository.UserRepository;

@Service
public class CustomOauth2UserDetailService extends DefaultOAuth2UserService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		return super.loadUser(userRequest);
	}
	
	private OAuth2User checkingOAuth2User(OAuth2UserRequest userRequest, OAuth2User auth2User) {
		Oauth2UserDetail userDetail = Oauth2UserDetailFactory.getOauth2UserDetail(
				userRequest.getClientRegistration().getRegistrationId(), auth2User.getAttributes());
		
		if(ObjectUtils.isEmpty(userDetail)) {
			throw new BaseException(String.valueOf(HttpStatus.NOT_FOUND),"cannot found user");
		}
		
		Optional<User> user = userRepository.findByEmailAndProviderId(
				userDetail.getGmail(),
				userRequest.getClientRegistration().getRegistrationId());
		User otherUser;
		
		if(user.isPresent()) {
			otherUser = user.get();
			
			if(!otherUser.getProviderId().equals(userRequest.getClientRegistration().getRegistrationId())) {
				throw new BaseException("400", "invalit site login with" + otherUser.getProviderId());
			}
		}
		
		return null;
	}
}

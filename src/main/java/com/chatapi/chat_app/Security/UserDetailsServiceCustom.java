package com.chatapi.chat_app.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;

import com.chatapi.chat_app.Entity.User;
import com.chatapi.chat_app.Exception.BaseException;
import com.chatapi.chat_app.Oauth2.UserDetailsCustom;
import com.chatapi.chat_app.repository.UserRepository;

public class UserDetailsServiceCustom implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsCustom userDetailsCustom = getUserDetailsCustom(username); 
		
		if(ObjectUtils.isEmpty(userDetailsCustom)){
            throw new UsernameNotFoundException("User not found");
        }
		
		return userDetailsCustom;
	}
	
	 private UserDetailsCustom getUserDetailsCustom(String username){
	        User user = userRepository.findByUsername(username).
	        		orElseThrow(() ->
	        						new BaseException(String.valueOf(HttpStatus.BAD_REQUEST), "User not found"));
	        
			return new UserDetailsCustom(
					user.getUserId(),
					user.getUsername(),
					List.of(new SimpleGrantedAuthority(user.getRole())),
					user.getPassword()
					);
	 }
}

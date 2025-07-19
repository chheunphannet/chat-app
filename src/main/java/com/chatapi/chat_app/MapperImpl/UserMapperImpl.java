package com.chatapi.chat_app.MapperImpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.chatapi.chat_app.Dto.UserDto;
import com.chatapi.chat_app.Entity.User;
import com.chatapi.chat_app.Enum.Provider;

@Service
public class UserMapperImpl {
	
	public User dtoToUser(UserDto dto) {
		if(dto == null) {
			return null;
		}
		
		return User.builder()
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.accessToken(null)
				.email(dto.getUserName())
				.profilePictureUrl(null)
				.createdAt(LocalDateTime.now())
				.birthday(dto.getBirthday())
				.password(dto.getPassword())
				.providerId(Provider.local.name())
				.build();
	}
}

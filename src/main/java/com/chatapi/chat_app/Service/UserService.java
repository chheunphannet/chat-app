package com.chatapi.chat_app.Service;

import com.chatapi.chat_app.Dto.UserDto;
import com.chatapi.chat_app.Entity.User;

public interface UserService {
	User register(UserDto dto);
	User loadUserByUserName(String username);
}

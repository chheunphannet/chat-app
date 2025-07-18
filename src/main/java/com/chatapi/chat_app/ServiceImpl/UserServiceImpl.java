package com.chatapi.chat_app.ServiceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.chatapi.chat_app.Dto.UserDto;
import com.chatapi.chat_app.Entity.User;
import com.chatapi.chat_app.Exception.BaseException;
import com.chatapi.chat_app.MapperImpl.UserMapperImpl;
import com.chatapi.chat_app.Service.UserService;
import com.chatapi.chat_app.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements UserService{
	@Autowired 
	private UserRepository userRepository;
	@Autowired 
	private UserMapperImpl userMapper; 
	
	@Override
	public User register(UserDto dto) {
		if(Objects.nonNull(loadUserByUserName(dto.getUserName()))) {
			throw new 
			BaseException(HttpStatus.BAD_REQUEST, "this {} already exit".formatted(dto.getUserName()));
		}else {
			return userRepository.save(userMapper.dtoToUser(dto));
		}
	}

	@Override
	public User loadUserByUserName(String usernameOrEmail) {
		return userRepository.findByEmail(usernameOrEmail).orElseThrow(()
				-> new BaseException(HttpStatus.NOT_FOUND, "this {} not found".formatted(usernameOrEmail)));
	}
	
	
	
//	private LocalDateTime toLocaDateTime(Instant exp) { 
//		 return exp.atZone(ZoneId.systemDefault()).toLocalDateTime();
//	}

}

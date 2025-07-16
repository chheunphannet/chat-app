package com.chatapi.chat_app.ServiceImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapi.chat_app.Service.UserService;
import com.chatapi.chat_app.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements UserService{
	@Autowired 
	private UserRepository userRepository;
	
	private LocalDateTime toLocaDateTime(Instant exp) { 
		 return exp.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}

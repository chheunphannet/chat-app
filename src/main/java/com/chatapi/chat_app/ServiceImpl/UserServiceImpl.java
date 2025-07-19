package com.chatapi.chat_app.ServiceImpl;

import static com.chatapi.chat_app.Enum.RoleEnum.USER;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.chatapi.chat_app.Dto.UserDto;
import com.chatapi.chat_app.Entity.Permission;
import com.chatapi.chat_app.Entity.Role;
import com.chatapi.chat_app.Entity.User;
import com.chatapi.chat_app.Enum.PermissonEnum;
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
		log.info(loadUserByUserName(dto.getUserName()).toString());
		if(loadUserByUserName(dto.getUserName()) == null) {
			User user = new User();
			user = userMapper.dtoToUser(dto);
			user.setRoles(Set.of(setRole()));
			return userRepository.save(user);
		}else {
			throw new BaseException(HttpStatus.BAD_REQUEST, "this " + dto.getUserName() + " is already exit");
		}
	} 

	@Override
	public User loadUserByUserName(String usernameOrEmail) {
		return userRepository.findByEmail(usernameOrEmail).orElse(null);
	}
	
	private Role setRole() {
		Set<Permission> permissions = new HashSet<>();
		Set<String> decription = new HashSet<>();
		
		for(PermissonEnum p : USER.getPermission()) {
			decription.add(p.getDecription());
		}
		
		for(String d : decription) {
			permissions.add(Permission.builder().name(d).build());
		}
		
		return Role.builder()
				.name(USER.name())
				.permissions(permissions)
				.build(); 
	}
	
//	private LocalDateTime toLocaDateTime(Instant exp) { 
//		 return exp.atZone(ZoneId.systemDefault()).toLocalDateTime();
//	}

}

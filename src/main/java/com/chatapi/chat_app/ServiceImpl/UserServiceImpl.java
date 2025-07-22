package com.chatapi.chat_app.ServiceImpl;

import static com.chatapi.chat_app.Enum.RoleEnum.USER;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.chatapi.chat_app.Dto.UserDto;
import com.chatapi.chat_app.Entity.Permission;
import com.chatapi.chat_app.Entity.Role;
import com.chatapi.chat_app.Entity.User;
import com.chatapi.chat_app.Enum.PermissonEnum;
import com.chatapi.chat_app.Enum.RoleEnum;
import com.chatapi.chat_app.Exception.BaseException;
import com.chatapi.chat_app.MapperImpl.UserMapperImpl;
import com.chatapi.chat_app.Service.UserService;
import com.chatapi.chat_app.repository.RoleRepository;
import com.chatapi.chat_app.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements UserService{
	@Autowired 
	private UserRepository userRepository;
	@Autowired 
	private UserMapperImpl userMapper; 
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public User register(UserDto dto) {
		if(loadUserByUserName(dto.getUserName()) == null) {
			User user = new User();
			user = userMapper.dtoToUser(dto);
			user.setRoles(Set.of(setRole(USER)));
			return userRepository.save(user);
		}else {
			throw new BaseException(HttpStatus.BAD_REQUEST, "this " + dto.getUserName() + " is already exit");
		}
	} 

	@Override
	public User loadUserByUserName(String usernameOrEmail) {
		return userRepository.findByEmail(usernameOrEmail).orElse(null);
	}
	
	private Role setRole(RoleEnum typeRole) {
		Set<String> decription = new HashSet<>();
		Set<Permission> permissions = new HashSet<>();
		 
		for(PermissonEnum p : typeRole.getPermission()) {
			decription.add(p.getDecription());
		}
		
		
		for(String d : decription) {
			permissions.add(Permission.
					builder()
					.name(d)
					.build());
		}
		
		
		Role role = roleRepo.findByName(typeRole.name());
		
		if(Objects.isNull(role)) {
			role = Role.builder()
			.name(typeRole.name())
			.permissions(permissions)
			.build(); 
		}
		
		return role;
	}
	
//	private LocalDateTime toLocaDateTime(Instant exp) { 
//		 return exp.atZone(ZoneId.systemDefault()).toLocalDateTime();
//	}

}

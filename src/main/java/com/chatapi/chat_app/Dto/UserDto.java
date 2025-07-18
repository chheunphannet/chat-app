package com.chatapi.chat_app.Dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
	private String firstName;
	private String lastName;
	private String userName;
	private LocalDate birthday;
	private String password;
}

package com.chatapi.chat_app.Enum;

import java.util.Set;

import lombok.Getter;

import static com.chatapi.chat_app.Enum.Permisson.*;
@Getter
public enum Role {
	USER(Set.of(block_users,receive_notifications,send_messages))
	,ADMIN(Set.of(block_users,receive_notifications,send_messages,ban_users));
	
	private Set<Permisson> permission;

	private Role(Set<Permisson> permission) {
		this.permission = permission;
	}
	
}

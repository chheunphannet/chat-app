package com.chatapi.chat_app.Enum;

import static com.chatapi.chat_app.Enum.PermissonEnum.*;

import java.util.Set;

import lombok.Getter;

@Getter
public enum RoleEnum {
	USER(Set.of(block_users, receive_notifications, send_messages)),
	ADMIN(Set.of(block_users, receive_notifications, send_messages, ban_users));

	private Set<PermissonEnum> permission;

	private RoleEnum(Set<PermissonEnum> permission) {
		this.permission = permission;
	}

}

package com.chatapi.chat_app.Enum;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Permisson {
	
	block_users("block:users"),
	send_messages("send:messages"),
	receive_notifications("receive:notifications"),
	ban_users("ban:user");
	
	private String decription;
}

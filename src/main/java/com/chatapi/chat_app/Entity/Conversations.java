package com.chatapi.chat_app.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Conversations")
@Data
public class Conversations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ConversationId;
	private String conversationType;
	private String title;
	private User createBy;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private Boolean isArchived;
}

package com.chatapi.chat_app.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Messages")
@Data
public class Messages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;
	private Conversations conversationId;
	private User senderId;
	private String conten;
	private String messageType;
	private String fileUrl;
	private Integer fileSize;
	private String fileType;
	private String status;
	private Boolean isDeleted;
	private LocalDateTime sendAt;
	private LocalDateTime deliveredAt;
	private LocalDateTime readAt;
	
}

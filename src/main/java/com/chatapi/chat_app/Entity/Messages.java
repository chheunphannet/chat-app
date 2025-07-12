package com.chatapi.chat_app.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*; // Make sure to import these
import lombok.Data;

@Entity
@Table(name = "Messages")
@Data
public class Messages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;

	// FIX 1: Corrected mapping to Conversations entity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	private Conversations conversation;

	// FIX 2: Corrected mapping to User entity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id") // Assumes foreign key column is "sender_id"
	private User sender;

	private String content;
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
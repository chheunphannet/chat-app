package com.chatapi.chat_app.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*; // Make sure to import these
import lombok.Data;

@Entity
@Table(name = "Messages")
@Data
public class Messages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	private Conversations conversation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id") // Assumes foreign key column is "sender_id"
	private User sender;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "message_type")
	private String messageType;
	
	@Column(name = "file_url")
	private String fileUrl;
	
	@Column(name = "file_size")
	private Integer fileSize;
	
	@Column(name = "file_type")
	private String fileType;
	
	@Column(name = "status")
	private String status = "sent";
	
	@Column(name = "is_deleted")
	private Boolean isDeleted = false;
	
	@CreationTimestamp
	@Column(name = "send_at")
	private LocalDateTime sendAt;
	
	@Column(name = "delivered_at")
	private LocalDateTime deliveredAt;
	
	@Column(name = "read_at")
	private LocalDateTime readAt;
}
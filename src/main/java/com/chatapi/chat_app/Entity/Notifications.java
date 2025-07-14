package com.chatapi.chat_app.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Notifications")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notifications {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long notificationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "related_user_id")
	private User relatedUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	private Conversations conversation;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "notification_type", nullable = false, length = 50)
	private String notificationType; 
	
	@Column(name = "sent_at")
	private LocalDateTime sentAt;
	
	@Column(name = "read_at")
	private LocalDateTime readAt;
	
	@Column(name = "expires_at")  
    private LocalDateTime expiresAt;
	
	
}

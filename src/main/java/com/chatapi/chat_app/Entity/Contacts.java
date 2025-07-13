package com.chatapi.chat_app.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Contacts", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "contact_user_id"}))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Contacts {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long contactId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_user_id", nullable = false)
	private User contactUser;
	
	@Column(name = "is_blocked")
	private Boolean isBlock = false;
	
	@Column(name = "active")
	private String status = "active"; //'active', -- 'active', 'deleted'
	
	@CreationTimestamp
	@Column(name = "added_at" ,updatable = false)
	private LocalDateTime addedAt;
	
	@Column(name = "last_contacted")
	private LocalDateTime lastContacted;
	
}

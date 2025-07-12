package com.chatapi.chat_app.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(nullable = false, length = 100)
	private String username;
	@Column(nullable = false, length = 10)
	private String firstName;
	@Column(nullable = false, length = 15)
	private String lastName;
	@Column(nullable = false, length = 255)
	private String email;
	private String profileUrl;
	private LocalDate birthday;
	private String accessToken;
	private LocalDateTime tokenExpiresAt;
	private String permissions;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createAt;
}

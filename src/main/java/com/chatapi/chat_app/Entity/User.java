package com.chatapi.chat_app.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include 
    private Long userId;
    
    @Column(name = "username", nullable = false, length = 100, unique = true)
    private String username;
    
    @Column(name = "email", length = 255)
    private String email;
    
    @Column(name = "first_name", length = 10)
    private String firstName;
    
    @Column(name = "last_name", length = 15)
    private String lastName;
    
    @Column(name = "profile_picture_url")
    private String profilePictureUrl;
    
    @Column(name = "birthday")
    private LocalDate birthday;
    
    @Column(name = "access_token")
    private String accessToken;
    
    @Column(name = "token_expires_at")
    private LocalDateTime tokenExpiresAt;
    
    @Column(name = "permissions")
    private String permissions;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConversationParticipants> conversationParticipants;
    
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messages> messages;
    
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Conversations> createdConversations;
    
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Contacts> contacts;
    
    @OneToMany(mappedBy = "contactUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Contacts> contactedBy; 
    
    @OneToMany(mappedBy = "userNotifications", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notifications> notifications;
    
    @OneToMany(mappedBy = "userRelated", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notifications> notificationsRelated;
    // Utility methods     
    public String getFullName() {
        return firstName + " " + lastName;
    }
     
    public boolean isTokenExpired() {
        return tokenExpiresAt != null && tokenExpiresAt.isBefore(LocalDateTime.now());
    }
}
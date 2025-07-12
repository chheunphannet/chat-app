package com.chatapi.chat_app.Entity;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "conversations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"participants", "messages", "creator"}) // Avoid circular references
public class Conversations {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    @EqualsAndHashCode.Include
    private Long conversationId;
    
    @Column(name = "conversation_type", nullable = false, length = 20)
    private String conversationType; // 'direct', 'group', 'channel'
    
    @Column(name = "title", length = 255)
    private String title; // Group name (null for direct)
    
    @Column(name = "created_by")
    private Long createdBy;
    
    // Optional: Many-to-One relationship to User who created the conversation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    private User creator;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived = false;
    
    // Bidirectional relationships (optional)
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConversationParticipants> participants;
    
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messages> messages;
    
    // Custom constructors
    public Conversations(String conversationType, String title, Long createdBy) {
        this.conversationType = conversationType;
        this.title = title;
        this.createdBy = createdBy;
        this.isArchived = false;
    }
    
    public Conversations(String conversationType, Long createdBy) {
        this(conversationType, null, createdBy);
    }
    
    // Utility methods
    public boolean isDirect() {
        return "direct".equals(this.conversationType);
    }
    
    public boolean isGroup() {
        return "group".equals(this.conversationType);
    }
    
    public boolean isChannel() {
        return "channel".equals(this.conversationType);
    }
    
    public void archive() {
        this.isArchived = true;
    }
    
    public void unarchive() {
        this.isArchived = false;
    }
}
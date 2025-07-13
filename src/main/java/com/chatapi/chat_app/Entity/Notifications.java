package com.chatapi.chat_app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private User user;
//		notification_id     LONG PRIMARY KEY,
//	    user_id            LONG NOT NULL,
//	    
//	    -- Notification Content
//	    title              VARCHAR(255),
//	    content            TEXT,
//	    notification_type  VARCHAR(50) NOT NULL,
//		conversation_id    LONG,                   -- Related conversation (if any)
//		sent_at            TIMESTAMP,
//		read_at            TIMESTAMP,
//	    expires_at         TIMESTAMP,
//		related_user_id    LONG,
//		
//		FOREIGN KEY (user_id) REFERENCES users(user_id),
//	    FOREIGN KEY (related_user_id) REFERENCES users(user_id),	 -- Who triggered this notification
//	    FOREIGN KEY (conversation_id) REFERENCES conversations(conversation_id)
	
}

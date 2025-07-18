package com.chatapi.chat_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapi.chat_app.Entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String username);
}

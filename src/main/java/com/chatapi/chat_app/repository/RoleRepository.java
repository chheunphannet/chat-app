package com.chatapi.chat_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapi.chat_app.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}

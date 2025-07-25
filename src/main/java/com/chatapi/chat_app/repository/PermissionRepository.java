package com.chatapi.chat_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapi.chat_app.Entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
	Permission findByName(String name); 
}

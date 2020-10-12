package com.alk.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alk.project.demo.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}

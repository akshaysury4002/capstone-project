package com.as.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.project.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}

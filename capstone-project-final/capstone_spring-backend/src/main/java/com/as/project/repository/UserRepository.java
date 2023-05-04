package com.as.project.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.project.domain.User;





public interface UserRepository extends JpaRepository<User, Long> {
   
    Optional<User> findByEmailAndPassword(String email, String password);

}

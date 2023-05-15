package com.as.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.project.domain.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
}

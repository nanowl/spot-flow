package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
  Optional<ChatLog> findById(Long id);
}

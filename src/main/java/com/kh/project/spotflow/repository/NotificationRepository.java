package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findNotificationByDiaryWriter(String email);

    List<Notification> findNotificationById(Long id);

}

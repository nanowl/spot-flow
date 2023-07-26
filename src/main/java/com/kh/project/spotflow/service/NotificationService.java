package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.ResponseNotification;
import com.kh.project.spotflow.model.dto.diary.DiaryResponseDto;
import com.kh.project.spotflow.model.dto.diary.request.DiaryUpdateRequest;
import com.kh.project.spotflow.model.entity.*;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.DiaryCommentRepository;
import com.kh.project.spotflow.repository.DiaryRepository;
import com.kh.project.spotflow.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final CustomerRepository customerRepository;


    public List<ResponseNotification> findMyNotice(HttpServletRequest request) {
        Customer customer = customerRepository.findCustomerByEmail(request.getHeader("Authorization")); // 수정 필요
        List<Notification> notificationList = notificationRepository.findNotificationByDiaryWriter(customer.getEmail());

        List<ResponseNotification> responseList = new ArrayList<>();

        for (Notification e : notificationList) {
            ResponseNotification responseNotification = new ResponseNotification().of(e);
            responseList.add(responseNotification);
        }

        return responseList;
    }

    @Transactional
    public void update(List<ResponseNotification> request) {
        List<Notification> notificationList = new ArrayList<>();
        for (ResponseNotification current : request) {
            if (!current.isRead()) {
                Notification notification = notificationRepository.findNotificationById(current.getId());
                notification.setRead(true);
                notificationList.add(notification);
            }
        }
        if (notificationList.size() > 0) {
            notificationRepository.saveAll(notificationList);
        }
    }
}
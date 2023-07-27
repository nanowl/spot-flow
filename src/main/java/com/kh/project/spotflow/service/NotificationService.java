package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.ResponseNotification;
import com.kh.project.spotflow.model.dto.comment.CommentRequest;
import com.kh.project.spotflow.model.dto.comment.CommentResponse;
import com.kh.project.spotflow.model.dto.diary.request.DiaryLikeRequest;
import com.kh.project.spotflow.model.dto.like.likeNotice;
import com.kh.project.spotflow.model.entity.*;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.DiaryRepository;
import com.kh.project.spotflow.repository.LikeRepository;
import com.kh.project.spotflow.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final CustomerRepository customerRepository;
    private final AuthService authService;
    private final DiaryRepository diaryRepository;
    private final LikeRepository likeRepository;


    public List<ResponseNotification> findMyNotice(HttpServletRequest request) {
        Customer customer = authService.getCustomerByEmail();
        log.info(customer.toString());
        List<Notification> notificationList = notificationRepository.findByDiaryWriterOrderByIdDesc(customer);
        log.info(notificationList.toString());

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

    public ResponseNotification save(CommentRequest request) {
        Diary diary = diaryRepository.findDiaryById(request.getDiary());
        Customer sender = authService.getCustomerByEmail();
        DiaryComment comment = request.toComment(sender, diary);
        Customer receiver = customerRepository.findCustomerByEmail(diary.getCustomer().getEmail());

        Notification notification = Notification.builder()
                .receiver(receiver)
                .diary(diary)
                .sender(sender)
                .diaryComment(comment)
                .isRead(false)
                .build();

        notificationRepository.save(notification);
        return new ResponseNotification().of(notification);
    }

    public likeNotice likeNotice(DiaryLikeRequest request) {
        Like like = likeRepository.findLikeById(request.getId());
        Customer sender = authService.getCustomerByEmail();
        Diary diary = like.getDiary();
        Customer receiver = diary.getCustomer();

//        Notification notification = Notification.builder()
//                .receiver(receiver)
//                .diary(diary)
//                .sender(sender)
//                .diaryComment(comment)
//                .isRead(false)
//                .build();

//        likeNotice notice = new likeNotice().of(no)
 return null;
    }
}
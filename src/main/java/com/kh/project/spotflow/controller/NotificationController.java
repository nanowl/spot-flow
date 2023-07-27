package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.ResponseNotification;
import com.kh.project.spotflow.model.dto.comment.CommentRequest;
import com.kh.project.spotflow.model.dto.diary.DiaryResponseDto;
import com.kh.project.spotflow.model.dto.diary.request.DiaryCreateRequest;
import com.kh.project.spotflow.model.dto.diary.request.DiaryUpdateRequest;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryItem;
import com.kh.project.spotflow.model.entity.Notification;
import com.kh.project.spotflow.repository.DiaryRepository;
import com.kh.project.spotflow.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping("/notification")
@RequiredArgsConstructor
@Slf4j
@RestController
@CrossOrigin("http://localhost:3000")
public class NotificationController {
    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;
    private final DiaryRepository diaryRepository;
    @PutMapping("/updatestatus")
    public void updateNotification(@RequestBody List<ResponseNotification> request) {
        notificationService.update(request);
    }
    // 알림 모두 불러오기
    @PostMapping("/getall")
    public ResponseEntity<List<ResponseNotification>> getAll(HttpServletRequest request) {

        return new ResponseEntity<>(notificationService.findMyNotice(request), HttpStatus.OK);
    }

    
    @PostMapping("/comment")
    public ResponseEntity<ResponseNotification> sendCommentNoti(@RequestBody CommentRequest request) {
        Long diaryNumber = request.getDiary();
        Diary diary = diaryRepository.findDiaryById(diaryNumber);
        Customer customer = diary.getCustomer();
        String email = customer.getEmail();
        String msg = "새 알림이 있습니다";
        messagingTemplate.convertAndSend("/region/" + email, msg);
        return new ResponseEntity<>(notificationService.save(request), HttpStatus.OK);
    }
}

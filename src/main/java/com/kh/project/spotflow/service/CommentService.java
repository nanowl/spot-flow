package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.comment.CommentRequest;
import com.kh.project.spotflow.model.dto.comment.CommentResponse;
import com.kh.project.spotflow.model.dto.comment.CommentUpdateRequest;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryComment;
import com.kh.project.spotflow.model.entity.Notification;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.DiaryCommentRepository;
import com.kh.project.spotflow.repository.DiaryRepository;
import com.kh.project.spotflow.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentService {
  private final DiaryCommentRepository commentRepository;
  private final DiaryRepository diaryRepository;
  private final CustomerRepository customerRepository;
  private final AuthService authService;
  private final NotificationRepository notificationRepository;
  private final SimpMessagingTemplate simpleMessagingTemplate;

  // 댓글 데이터를 저장
  @Transactional
  public CommentResponse saveComment(CommentRequest request) {
    Diary diary = diaryRepository.findDiaryById(request.getDiary());
    Customer commentWriter = authService.getCustomerByEmail();
    DiaryComment comment = request.toComment(commentWriter, diary);
    commentRepository.save(comment);

    Customer diaryWriter = customerRepository.findCustomerByEmail(diary.getCustomer().getEmail());

    Notification notification = Notification.builder()
            .diaryWriter(diaryWriter)
            .diary(diary)
            .diaryComment(comment)
            .isRead(false)
            .build();

    notificationRepository.save(notification);
    String email = diaryWriter.getEmail();
    String msg = "새 알림이 있습니다";
    simpleMessagingTemplate.convertAndSend("/app/sendMessage/" + email, msg);

    return new CommentResponse().of(comment);
  }

  // 댓글 수정
  @Transactional
  public CommentResponse updateComment(CommentUpdateRequest request) {
    DiaryComment comment = commentRepository.findDiaryCommentById(request.getComment());
    comment.setContent(request.getContent());
    comment.setUpdate(LocalDateTime.now());
    commentRepository.save(comment);
    return new CommentResponse().of(comment);
  }

  // 댓글 삭제
  @Transactional
  public CommentResponse deleteComponent(Long request) {
    DiaryComment comment = commentRepository.findDiaryCommentById(request);
    comment.setDelete(true);
    commentRepository.save(comment);
    return new CommentResponse().of(comment);
  }
}

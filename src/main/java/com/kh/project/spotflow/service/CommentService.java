package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.comment.CommentRequest;
import com.kh.project.spotflow.model.dto.comment.CommentResponse;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryComment;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.DiaryCommentRepository;
import com.kh.project.spotflow.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentService {
  private final DiaryCommentRepository commentRepository;
  private final DiaryRepository diaryRepository;
  private final CustomerRepository customerRepository;

  // 댓글 데이터를 저장
  @Transactional
  public CommentResponse saveComment(CommentRequest request) {
    Diary diary = diaryRepository.findDiaryById(request.getDiary());
    Customer customer = customerRepository.findCustomerByEmail(request.getEmail());
    DiaryComment comment = request.toComment(customer, diary);
    commentRepository.save(comment);
    return new CommentResponse().of(comment);
  }
}

package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.comment.CommentRequest;
import com.kh.project.spotflow.model.dto.comment.CommentResponse;
import com.kh.project.spotflow.model.dto.comment.CommentUpdateRequest;
import com.kh.project.spotflow.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/diary/comment")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
@Slf4j
@RestController
public class DiaryCommentController {
  private final CommentService commentService;

  @PostMapping("")
  public ResponseEntity<CommentResponse> saveComment(@RequestBody CommentRequest request) {
    return new ResponseEntity<>(commentService.saveComment(request), HttpStatus.OK);
  }

  @PutMapping("")
  public ResponseEntity<CommentResponse> updateComment(@RequestBody CommentUpdateRequest request) {
    return new ResponseEntity<>(commentService.updateComment(request), HttpStatus.OK);
  }
}

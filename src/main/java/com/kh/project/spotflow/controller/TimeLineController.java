package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.TimeLine.TimeLineDto;
import com.kh.project.spotflow.model.dto.TimeLine.TimeLineRequestDto;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.service.TimeLineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/timeline")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TimeLineController {
     private final TimeLineService timeLineService;
     
     // 글쓰기
     @PostMapping("/post")
     public ResponseEntity<TimeLine> addPost(@RequestBody TimeLineRequestDto request) {
          return new ResponseEntity<>(timeLineService.createPost(request), HttpStatus.CREATED);
     }
     
}
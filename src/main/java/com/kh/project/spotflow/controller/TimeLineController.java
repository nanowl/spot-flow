package com.kh.project.spotflow.controller;


import com.kh.project.spotflow.model.dto.TimeLineDto;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.service.TimeLineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/timeline")
@CrossOrigin(origins = "*")
public class TimeLineController {
    private final TimeLineService timeLineService;


    // 이거 왜 post 가지고 옴>?????/
    @PostMapping("/find")
    public ResponseEntity<List<TimeLineDto>> find() {
        List<TimeLineDto> result = timeLineService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @GetMapping("/testing")
    public ResponseEntity<List<TimeLineDto>> testing(){
        List<TimeLineDto> result = timeLineService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/dummy")
    public void addPosts(@RequestBody Map<String, Object> request) {
        int count = (int) request.get("count");
        String userEmail = (String) request.get("userEmail");
        log.info(count + "개의 타임라인 포스트를 만듭니다.");
        timeLineService.createPosts(count, (userEmail));
    }

    @PostMapping("/post")
    public void addPost(@RequestBody TimeLineDto request) {
        String userEmail = (String) request.getCustomer().getEmail() ;
        log.info(userEmail);
        timeLineService.createPost(request);
    }

}

package com.kh.project.spotflow.controller;


import com.kh.project.spotflow.model.dto.TimeLineDto;
import com.kh.project.spotflow.service.TimeLineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/timeline")
public class TimeLineController {
    private final TimeLineService timeLineService;

    @PostMapping("/find")
    public ResponseEntity<List<TimeLineDto>> find() {
        List<TimeLineDto> result = timeLineService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/dummy")
    public void addPosts(@RequestBody Map<String, Object> request) {
        int count = (int) request.get("count");
         String userEmail = (String) request.get("userEmail");
        log.info(count + "개의 타임라인 포스트를 만듭니다.");
        timeLineService.createPosts(count, (userEmail));
    }

}

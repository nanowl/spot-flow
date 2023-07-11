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

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/timeline")
public class TimeLineController {
    private final TimeLineService timeLineService;

    @GetMapping("/list")
    public List<TimeLine> find() {
        return timeLineService.findAll();
    }

    @PostMapping("/post")
    public TimeLine addPosts(@RequestBody TimeLineDto request) {
        String userEmail = (String) request.getUserEmail();
        return timeLineService.createPost(request);
    }






}

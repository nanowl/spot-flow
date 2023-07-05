package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.TimelineDTO;
import com.kh.project.spotflow.service.TimeLineService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/timeline")
@RequiredArgsConstructor
public class TimelineController {
    private final TimeLineService timeLineService;

    // 이메일로 타임라인 조회
    @GetMapping("/find")
    public ResponseEntity<List<TimelineDTO>> getTimelineByEmail() {
        List<TimelineDTO> timelineDTOS = timeLineService.findAll();
        return new ResponseEntity<>(timelineDTOS, HttpStatus.OK);
    }

}

package com.kh.project.spotflow.controller;


import com.kh.project.spotflow.service.TimeLineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/timeline")
public class TimeLineController {
    private final TimeLineService timeLineService;


    @PostMapping("/find")
    public void find() {
        timeLineService.findAll();
    }

}

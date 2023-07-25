package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.ResponseNotification;
import com.kh.project.spotflow.model.dto.diary.DiaryResponseDto;
import com.kh.project.spotflow.model.dto.diary.request.DiaryCreateRequest;
import com.kh.project.spotflow.model.dto.diary.request.DiaryUpdateRequest;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.Notification;
import com.kh.project.spotflow.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping("/notification")
@RequiredArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class NotificationController {
    private final NotificationService notificationService;
//    @PutMapping("/updatestatus")
//    public ResponseEntity<DiaryResponseDto> updateNotification(@RequestBody DiaryUpdateRequest diaryRequest) {
//        return new ResponseEntity<>(diaryService.update(diaryRequest), HttpStatus.OK);
//    }
    // 알림 모두 불러오기
    @PostMapping("/getall")
    public ResponseEntity<List<ResponseNotification>> getAll(HttpServletRequest request) {

        return new ResponseEntity<>(notificationService.findMyNotice(request), HttpStatus.OK);
    }
}

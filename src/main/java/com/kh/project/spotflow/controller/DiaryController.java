package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.diary.DiaryRequestDto;
import com.kh.project.spotflow.model.dto.diary.DiaryResponseDto;
import com.kh.project.spotflow.model.dto.diary.DiaryUpdateRequest;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/diary")
@RequiredArgsConstructor
@Slf4j
@RestController
@CrossOrigin("http://localhost:3000")
public class DiaryController {
  private final DiaryService diaryService;

  @GetMapping("")
  public ResponseEntity<DiaryResponseDto> findDiary(@RequestParam("num") Long num) {
    return new ResponseEntity<>(diaryService.findDiaryById(num), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Diary>> findByMyDiaryList(@RequestParam("email") String email) {
    return new ResponseEntity<>(diaryService.findDiaryByMember(email), HttpStatus.OK);
  }

  @DeleteMapping("")
  public  ResponseEntity<DiaryResponseDto> deleteMyDiary(@RequestBody DiaryUpdateRequest diaryRequest) {
    return new ResponseEntity<>(diaryService.delete(diaryRequest),HttpStatus.OK);
  }
  @PutMapping("")
  public  ResponseEntity<DiaryResponseDto> updateMyDiary(@RequestBody DiaryUpdateRequest diaryRequest) {
    return new ResponseEntity<>(diaryService.update(diaryRequest),HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<Diary> save(@RequestBody DiaryRequestDto requestDto) {
    return new ResponseEntity<>(diaryService.save(requestDto), HttpStatus.OK);
  }
}

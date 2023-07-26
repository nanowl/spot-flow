package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.TimeLine.TimeLineRequestDto;
import com.kh.project.spotflow.model.dto.diary.request.DiaryCreateRequest;
import com.kh.project.spotflow.model.dto.diary.DiaryResponseDto;
import com.kh.project.spotflow.model.dto.diary.request.DiaryDeleteRequest;
import com.kh.project.spotflow.model.dto.diary.request.DiaryLikeRequest;
import com.kh.project.spotflow.model.dto.diary.request.DiaryUpdateRequest;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.Like;
import com.kh.project.spotflow.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/diary")
@RequiredArgsConstructor
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class DiaryController {
  private final DiaryService diaryService;

  // 다이어리 식별값만 받아서 상세 데이터를 제공
  @GetMapping("")
  public ResponseEntity<DiaryResponseDto> findDiary(@RequestParam("num") Long num) {
    return new ResponseEntity<>(diaryService.findDiaryById(num), HttpStatus.OK);
  }
  // 특정 유저가 작성한 다이어리를 모두 제공 (포함된 타임라인은 제공하지 않기 때문에 위 상세데이터는 따로 가져와야 함)
  @GetMapping("/all")
  public ResponseEntity<List<DiaryResponseDto>> findByMyDiaryList(@RequestParam("email") String email) {
    return new ResponseEntity<>(diaryService.findDiaryByMember(email), HttpStatus.OK);
  }

  // 체크한값들 삭제
  @DeleteMapping("/check")
  public ResponseEntity<List<DiaryResponseDto>> deleteMyDiarys(@RequestBody DiaryDeleteRequest request) {
    return new ResponseEntity<>(diaryService.checkDelete(request.getId()), HttpStatus.OK);
  }

  // 특정 다이어리를 삭제처리
  @DeleteMapping("/diary/check")
  public  ResponseEntity<DiaryResponseDto> deleteMyDiary(@PathVariable DiaryUpdateRequest diaryRequest) {
    return new ResponseEntity<>(diaryService.delete(diaryRequest),HttpStatus.OK);
  }
  // 특정 다이어리의 포함된 타임라인 리스트, 타이틀, 컨텐츠를 변경
  @PutMapping("")
  public  ResponseEntity<DiaryResponseDto> updateMyDiary(@RequestBody DiaryUpdateRequest diaryRequest) {
    return new ResponseEntity<>(diaryService.update(diaryRequest),HttpStatus.OK);
  }
  // 다이어리 생성
  @PostMapping("")
  public ResponseEntity<Diary> save(@RequestBody DiaryCreateRequest requestDto) {
    return new ResponseEntity<>(diaryService.save(requestDto), HttpStatus.OK);
  }
  // 조회수 1up
  @PutMapping("/view")
  public ResponseEntity<DiaryResponseDto> viewUp(@RequestBody DiaryUpdateRequest request) {
    return new ResponseEntity<>(diaryService.viewUp(request), HttpStatus.OK);
  }
  // 좋아요 1up
  @PutMapping("/like")
  public ResponseEntity<Integer> likeUp(@RequestBody DiaryLikeRequest request) {
    return new ResponseEntity<>(diaryService.likeControl(request), HttpStatus.OK);
  }

  @GetMapping("/like")
  public ResponseEntity<Like> findLikeInfo(@RequestBody DiaryLikeRequest request) {
    return new ResponseEntity<>(diaryService.likeInfo(request), HttpStatus.OK);
  }

  // 좋아요 집계
  @GetMapping("/like/count")
  public ResponseEntity<Long> countLike(@RequestParam("id") Long id) {
    return new ResponseEntity<>(diaryService.countLike(id), HttpStatus.OK);
  }

  @GetMapping("/following")
  public ResponseEntity<List<Diary>> friendDiary(@RequestParam("email") String email) {
    return new ResponseEntity<>(diaryService.friendDiaryList(email) , HttpStatus.OK);
  }

  // 특정 다이어리를 삭제처리



  @PostMapping("/search")
  public ResponseEntity<List<DiaryResponseDto>> searchDiary(@RequestBody TimeLineRequestDto request) {
    String place = request.getPlace();
    return new ResponseEntity<>(diaryService.findDiaryByFlow(place),HttpStatus.OK);
  }

}

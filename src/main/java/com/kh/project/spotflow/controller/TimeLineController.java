package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.ResponseTimeLine;
import com.kh.project.spotflow.model.dto.TimeLine.TimeLineDto;
import com.kh.project.spotflow.model.dto.TimeLine.TimeLineMyRequestDto;
import com.kh.project.spotflow.model.dto.TimeLine.TimeLineMyResponseDto;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/timeline")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TimeLineController {
    private final TimeLineService timeLineService;

    @GetMapping("/find")
    public ResponseEntity<List<ResponseTimeLine>> find() {
        List<ResponseTimeLine> result = timeLineService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    // 개인 타임라인 조회
    @GetMapping("/myflow/getmyflow")
    public ResponseEntity<List<TimeLineMyResponseDto>> getMyTimeLine() {
        List<TimeLineMyResponseDto> timeLineMyRequestDtoList = timeLineService.getMyTimeLine();
        return new ResponseEntity<>(timeLineMyRequestDtoList, HttpStatus.OK);
    }
    
    // 타임라인 저장
    @PostMapping("/myflow/myflownew")
    public ResponseEntity<List<TimeLineMyResponseDto>> saveMyTimeLine(@RequestBody TimeLineMyRequestDto timeLineMyRequestDto) {
        List<TimeLineMyResponseDto> timeLineMyRequestDtoList = timeLineService.saveTimeLine(timeLineMyRequestDto);
        return new ResponseEntity<>(timeLineMyRequestDtoList, HttpStatus.OK);
    }
    

    // 서버에서 처리하는 조회수 증가
    @PutMapping("/{postId}/views")
    public ResponseEntity<Void> increaseViewCount(@PathVariable Long postId, HttpServletRequest request, HttpServletResponse response) {
        timeLineService.increaseViewCount(postId, request, response);
        return ResponseEntity.ok().build();
    }

    // 타임라인 장소명 검색
    @GetMapping("/search")
    public List<TimeLineDto> searchPlace(@RequestParam String place) {
        TimeLineRequestDto request = new TimeLineRequestDto();
        request.setPlace(place);
        return timeLineService.searchPlace(place);
    }


    @GetMapping("/testing")
    public ResponseEntity<List<TimeLineDto>> testing(@RequestParam(value ="lastTimeLineId" , required = false) Long lastTimeLineId){
        List<TimeLineDto> result = timeLineService.getAll(lastTimeLineId,4);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/post")
    public ResponseEntity<TimeLine> addPost(@RequestBody TimeLineRequestDto request) {
        return new ResponseEntity<>(timeLineService.createPost(request), HttpStatus.CREATED);
    }

}

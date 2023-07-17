package com.kh.project.spotflow.controller;
import com.kh.project.spotflow.model.dto.TimeLineMyFlowGetDto;
import com.kh.project.spotflow.model.dto.chat.MyFlowRequestDto;
import com.kh.project.spotflow.service.TimeLineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/myflow")
public class TimeLineController {
    private final TimeLineService myFlowService;
    
    //나의 타임라인 정보 가죠오기
    @PostMapping("/getmyflow")
    public ResponseEntity <List<TimeLineMyFlowGetDto>> myFlowInfoGet(HttpServletRequest request) {
        List<TimeLineMyFlowGetDto> myFlowGetDtoList = myFlowService.getAll(request);
        return new ResponseEntity<>(myFlowGetDtoList, HttpStatus.OK);
    }
    
    //타임라인 글 작성하기
    @PostMapping("/myflownew")
    public boolean myFlowNewFlow(@RequestBody MyFlowRequestDto myFlowRequestDto, HttpServletRequest request) {
        return myFlowService.saveTimeLine(request, myFlowRequestDto);
    }
    

    @PostMapping("/selectedflow")
    public ResponseEntity <List<TimeLineMyFlowGetDto>> myflowSelected(HttpServletRequest request, Long id) {
        List<TimeLineMyFlowGetDto> myFlowGetDtoList = myFlowService.getSelected(request, id);
        return new ResponseEntity<>(myFlowGetDtoList, HttpStatus.OK);
    }
}

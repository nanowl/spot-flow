package com.kh.project.spotflow.controller;
import com.kh.project.spotflow.model.dto.MyFlowGetDto;
import com.kh.project.spotflow.model.dto.chat.MyFlowRequestDto;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.service.MyFlowService;
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
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/myflow")
public class MyFlowController {
    private final MyFlowService myFlowService;
    @PostMapping("/getmyflow")
    public ResponseEntity <List<MyFlowGetDto>> myFlowInfoGet(HttpServletRequest request) {
        List<MyFlowGetDto> myFlowGetDtoList = myFlowService.getAll(request);
        return new ResponseEntity<>(myFlowGetDtoList, HttpStatus.OK);
    }

    @PostMapping("/myflownew")
    public boolean myFlowNewFlow(@RequestBody MyFlowRequestDto myFlowRequestDto, HttpServletRequest request) {
        return myFlowService.saveTimeLine(request, myFlowRequestDto);
    }

    @PostMapping("/selectedflow")
    public ResponseEntity <List<MyFlowGetDto>> myflowSelected(HttpServletRequest request, Long id) {
        List<MyFlowGetDto> myFlowGetDtoList = myFlowService.getSelected(request, id);
        return new ResponseEntity<>(myFlowGetDtoList, HttpStatus.OK);
    }
}

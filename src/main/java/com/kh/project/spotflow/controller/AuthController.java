package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.MemberRequestDto;
import com.kh.project.spotflow.model.dto.MemberResponseDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.service.AuthService;
import com.kh.project.spotflow.service.MyFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  private final MyFlowService myFlowService;

  @PostMapping("/signup")
  public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto) {
    return ResponseEntity.ok(authService.signup(requestDto));
  }

  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto) {
    return ResponseEntity.ok(authService.login(requestDto));
  }

  @PostMapping("/myflow")
  public ResponseEntity<Optional<TimeLine>> myFlowInfoGet(String email) {
    return ResponseEntity.ok(myFlowService.findByEmail(email));
  }

  @PostMapping("/myflownew")
  public boolean myFlowNewFlow(@RequestBody Map<String, String> data) {
    String email = data.get("email");
    String latStr = data.get("lat");
    String lngStr = data.get("lng");
    String content = data.get("content");
    String img = data.get("img");
    String flowIdStr = data.get("flowId");

    Double lat = Double.valueOf(latStr);
    Double lng = Double.valueOf(lngStr);
    Long flowId = Long.valueOf(flowIdStr);

    TimeLine timeLine = new TimeLine();
    timeLine.setEmail(email);
    timeLine.setId(flowId);
    timeLine.setLat(lat);
    timeLine.setLng(lng);
    timeLine.setTl_profile_pic(img);
    timeLine.setContent(content);

    myFlowService.saveTimeLine(timeLine);
    return true;
  }
}
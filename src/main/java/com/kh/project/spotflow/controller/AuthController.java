package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.MemberRequestDto;
import com.kh.project.spotflow.model.dto.MemberResponseDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.service.AuthService;
import com.kh.project.spotflow.service.MyFlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
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
  public ResponseEntity<Optional<TimeLine>> myFlowInfoGet(Member member) {
    return ResponseEntity.ok(myFlowService.findByMember(member));
  }

  @PostMapping("/myflownew")
  public boolean myFlowNewFlow(@RequestBody Map<String, String> data) {
    String email = data.get("email");
    String latStr = data.get("lat");
    String lngStr = data.get("lng");
    String content = data.get("content");
    String img = data.get("img");
    String flowIdStr = data.get("flowId");
    String place = data.get("place");

    Double lat = Double.valueOf(latStr);
    Double lng = Double.valueOf(lngStr);
    Long flowId = Long.valueOf(flowIdStr);
    return myFlowService.saveTimeLine(email, lat, lng, content, img, flowId, place);
  }

  @PostMapping("/dummy")
  public ResponseEntity<List<Member>> addUser(@RequestBody Map<String, Object> request) {
    int count = (int) request.get("count");
    log.info(count + "명의 유저를 만듭니다.");
    return new ResponseEntity<>(authService.saveUser(count), HttpStatus.OK);
  }
}
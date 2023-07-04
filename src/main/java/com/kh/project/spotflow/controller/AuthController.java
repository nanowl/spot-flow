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
}
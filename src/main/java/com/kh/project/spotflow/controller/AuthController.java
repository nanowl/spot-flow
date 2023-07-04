package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.MemberRequestDto;
import com.kh.project.spotflow.model.dto.MemberResponseDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto) {
    return ResponseEntity.ok(authService.signup(requestDto));
  }

  @PostMapping("/dummy/signup")
  public ResponseEntity<List<MemberResponseDto>> signupDummy() {
    return ResponseEntity.ok(authService.signupDummy(1000));
  }

  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto) {
    return ResponseEntity.ok(authService.login(requestDto));
  }
}
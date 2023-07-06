package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.CustomerRequestDto;
import com.kh.project.spotflow.model.dto.CustomerResponseDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.service.AuthService;
import com.kh.project.spotflow.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  private final EmailService emailService;
  
  //이메일 중복 확인
  @GetMapping("/check-duplicate-email")
  public ResponseEntity<Boolean> checkEmailDuplicate(@RequestParam String email) {
    return ResponseEntity.ok(authService.checkEmailDuplicate(email));
  }
  
  
  @PostMapping("/signup")
  public ResponseEntity<CustomerResponseDto> signup(@RequestBody CustomerRequestDto requestDto) {
    return ResponseEntity.ok(authService.signup(requestDto));
  }
  
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody CustomerRequestDto requestDto) {
    return ResponseEntity.ok(authService.login(requestDto));
  }
  
  @PostMapping("/emailConfirm")
  public String emailConfirm(@RequestParam String email) throws Exception {
    String confirm = emailService.sendSimpleMessage(email);
    return confirm;
  }
  

  
}
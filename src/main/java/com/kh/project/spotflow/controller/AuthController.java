package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.CustomerRequestDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.service.AuthService;
import com.kh.project.spotflow.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000")

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
  private final AuthService authService;
  private final EmailService emailService;
  
  //이메일 중복 확인 true: email 이미 있음 false: email 없음
  @GetMapping("/check-duplicate-email")
  public ResponseEntity<Boolean> checkEmailDuplicate(@RequestParam String email) {
    return ResponseEntity.ok(authService.checkEmailDuplicate(email));
  }
  
  // 이메일 인증시 키 전송
  @GetMapping("/emailauth")
  public ResponseEntity<String> emailAuthentication(@RequestParam String email) throws Exception {
    System.out.print("email : " + email);
    String code = emailService.sendSimpleMessage(email);
    System.out.print("email code : " + code);
    return new ResponseEntity<>(code, HttpStatus.OK);
  }
  
  // 이메일 이증하기
  @GetMapping("/emailauth/confirm")
  public ResponseEntity<Boolean> emailAuthenticationConfirm(@RequestParam String email, @RequestParam String key) {
    boolean isConfirm = false;
    String confirmKey = emailService.getConKey(email);
    if (confirmKey.equals(key)) {
      isConfirm = true;
      emailService.deleteConKey(email);
    }
    return new ResponseEntity<>(isConfirm, HttpStatus.OK);
  }
  
  //닉내임 중복 확인 true: nickname 이미 있음 false : nickname 없음
  @GetMapping("/check-duplicate-nickname")
  public ResponseEntity<Boolean> checkNickNameDuplicate(@RequestParam String nickName){
    return ResponseEntity.ok(authService.checkNickNameDuplicate(nickName));
  }
  
  //회원 가입
  @PostMapping("/signup")
  public ResponseEntity<Boolean> signup(@RequestBody CustomerRequestDto requestDto) {
    return ResponseEntity.ok(authService.signup(requestDto));
  }
  
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody CustomerRequestDto requestDto) {
    return ResponseEntity.ok(authService.login(requestDto));
  }
  @PostMapping("/dummy")
  public ResponseEntity<List<Customer>> addUser(@RequestBody Map<String, Object> request) {
    int count = (int) request.get("count");
    log.info(count + "명의 유저를 만듭니다.");
    return new ResponseEntity<>(authService.saveUser(count), HttpStatus.OK);
  }


  @PostMapping("/dummyflow")
  public ResponseEntity<List<TimeLine>> addFlow(@RequestBody Map<String, Object> request) {
    int count = (int) request.get("count");
    log.info(count + "개의 글을 만듭니다.");
    authService.saveFlow(count);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
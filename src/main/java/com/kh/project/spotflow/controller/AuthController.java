package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.CustomerRequestDto;
import com.kh.project.spotflow.model.dto.CustomerResponseDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< Updated upstream
import java.util.List;

=======
import java.util.Map;

@CrossOrigin("http://localhost:3000")
>>>>>>> Stashed changes
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  
  //이메일 중복 확인
  @GetMapping("/check-duplicate-email")
  public ResponseEntity<Boolean> checkEmailDuplicate(@RequestParam String email) {
    return ResponseEntity.ok(authService.checkEmailDuplicate(email));
  }
  @PostMapping("/signup")
  public ResponseEntity<CustomerResponseDto> signup(@RequestBody CustomerRequestDto requestDto) {
    return ResponseEntity.ok(authService.signup(requestDto));
  }
<<<<<<< Updated upstream

  @PostMapping("/dummy/signup")
  public ResponseEntity<List<MemberResponseDto>> signupDummy() {
    return ResponseEntity.ok(authService.signupDummy(1000));
  }

=======
  
>>>>>>> Stashed changes
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody CustomerRequestDto requestDto) {
    return ResponseEntity.ok(authService.login(requestDto));
  }
  

  
}
package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.CustomerRequestDto;
import com.kh.project.spotflow.model.dto.CustomerResponseDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.config.jwt.TokenProvider;
import com.kh.project.spotflow.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
  private final AuthenticationManagerBuilder managerBuilder;
  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;
  
  //이메일 중복 체크
  public boolean checkEmailDuplicate(String email) {
    return customerRepository.existsByEmail(email);
  }
  
  //닉네임 중복 체크
  public boolean checkNickNameDuplicate(String nickName) {
    return customerRepository.existsByNickName(nickName);
  }
  
  // 회원 가입
  public boolean signup (CustomerRequestDto requestDto){
    if (customerRepository.existsByEmail(requestDto.getEmail())) {
      throw new RuntimeException("이미 가입되어 있는 유저입니다");
    }
    Customer customer = requestDto.toMember(passwordEncoder);
    CustomerResponseDto.of(customerRepository.save(customer));
    return true;
  }

  public boolean signupDummy(int count) {
    for (int i = 0; i < count; i++) {
      CustomerRequestDto requestDto = new CustomerRequestDto();

      signup(requestDto);
    }

    return true;
  }


  
  //로그인시 토큰값 전달
  public TokenDto login (CustomerRequestDto requestDto){
    UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
    Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
    return tokenProvider.generateTokenDto(authentication);
  }
  

}
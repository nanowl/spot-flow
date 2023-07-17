package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.constant.Authority;
import com.kh.project.spotflow.model.constant.OpenStatus;
import com.kh.project.spotflow.model.constant.Theme;
import com.kh.project.spotflow.model.dto.CustomerRequestDto;
import com.kh.project.spotflow.model.dto.CustomerResponseDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.config.jwt.TokenProvider;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
  private final AuthenticationManagerBuilder managerBuilder;
  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;
  private final TimeLineRepository timeLineRepository;
  
  //이메일 중복 체크
  public boolean checkEmailDuplicate(String email) {
    return customerRepository.existsByEmail(email);
  }
  
  //닉네임 중복 체크
  public boolean checkNickNameDuplicate(String nickName) {
    return customerRepository.existsByNickName(nickName);
  }
  
  // 회원 가입
  public boolean signup(CustomerRequestDto requestDto) {
    if (customerRepository.existsByEmail(requestDto.getEmail())) {
      throw new RuntimeException("이미 가입되어 있는 유저입니다");
    }
    Customer customer = requestDto.toMember(passwordEncoder);
    CustomerResponseDto.of(customerRepository.save(customer));
    return true;
  }
  
  //로그인시 토큰값 전달
  public TokenDto login(CustomerRequestDto requestDto) {
    UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
    Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
    return tokenProvider.generateTokenDto(authentication);
  }
  
  //토근검증후 로그인 정호 Customer 정보 공유하기
  public Customer validateTokenGetCustomerInfo(HttpServletRequest request) {
    String accessToken = request.getHeader("Authorization");
    if (accessToken != null && accessToken.startsWith("Bearer ")) {
      accessToken = accessToken.substring(7);
    }
    
    if (!tokenProvider.validateToken(accessToken)) {
      throw new IllegalArgumentException("없는 토큰 정보");
    }
    
    UserDetails userDetails = (UserDetails) tokenProvider.getAuthentication(accessToken).getPrincipal();
    String email = userDetails.getUsername();
    
    return customerRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없음"));
  }

  
  //User Dummy Data 만들기
  public List<Customer> saveUser(int count) {
    List<Customer> customers = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Customer customer = Customer.builder()
        .email("testAccount" + i)
        .password("1234")
        .authority(Authority.ROLE_USER)
        .nickName("nickname" + i)
        .joinDate(LocalDateTime.now())
        .openStatus(OpenStatus.PUBLIC)
        .theme(Theme.LIGHT_MODE)
        .build();
      customers.add(customer);
      log.info("count : " + i);
    }
    customerRepository.saveAll(customers);
    return customers;
  }
  
  // 타임라인 정보 DummyData 넣기
  public void saveFlow(int count) {
    for (int i = 0; i < count; i++) {
      TimeLine timeLine = new TimeLine();
      LocalDateTime localDateTime = LocalDateTime.now();
      Customer customer = customerRepository.findCustomerByEmail("youngtong111@naver.com");
      customer.getTimeLineList().add(timeLine);
      timeLine.setCustomer(customer);
      timeLine.setId((long) (i + 1));
      timeLine.setLat((double) 0);
      timeLine.setLng((double) 0);
      timeLine.setImage("https://firebasestorage.googleapis.com/v0/b/spotflow-5475a.appspot.com/o/KakaoTalk_20230703_002523159.png?alt=media&token=8373de55-19f8-490e-a80c-487441b497e");
      timeLine.setContent("마이플로우샘플입니다마이플로우샘플입니다마이플로우샘플입니다마이플로우샘플입니다");
      timeLine.setPlace("홍대" + i);
      timeLine.setJoinDate(localDateTime);
      timeLine.setView(0);
      timeLineRepository.save(timeLine);
    }
  }
}
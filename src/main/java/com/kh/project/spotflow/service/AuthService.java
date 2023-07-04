package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.MemberRequestDto;
import com.kh.project.spotflow.model.dto.MemberResponseDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.config.jwt.TokenProvider;
import com.kh.project.spotflow.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
  private final AuthenticationManagerBuilder managerBuilder;
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;
  private final MemberService memberService;

  public MemberResponseDto signup(MemberRequestDto requestDto) {
    if (memberRepository.existsByEmail(requestDto.getEmail())) {
      throw new RuntimeException("이미 가입되어 있는 유저입니다");
    }

    Member member = requestDto.toMember(passwordEncoder);
    return MemberResponseDto.of(memberRepository.save(member));
  }


  public List<MemberResponseDto> signupDummy(int count) {
    List<MemberResponseDto> responseDtos = new ArrayList<>();
    List<Member> members = memberService.saveUser(count);
    for (int i = 0; i < count; i ++) {
      Member member = members.get(i);
      responseDtos.add(MemberResponseDto.of(memberRepository.save(member)));
    }
    return responseDtos;
  }

  public TokenDto login(MemberRequestDto requestDto) {
    UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
    log.info("토큰 메소드 : " + authenticationToken.toString());
    Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
    log.info(authentication.toString());
    return tokenProvider.generateTokenDto(authentication);
  }
}

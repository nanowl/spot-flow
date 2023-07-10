package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.constant.Authority;
import com.kh.project.spotflow.model.constant.OpenStatus;
import com.kh.project.spotflow.model.constant.Theme;
import com.kh.project.spotflow.model.dto.MemberRequestDto;
import com.kh.project.spotflow.model.dto.MemberResponseDto;
import com.kh.project.spotflow.model.dto.TokenDto;
import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.config.jwt.TokenProvider;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.MemberRepository;
import com.kh.project.spotflow.repository.MyFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
  private final AuthenticationManagerBuilder managerBuilder;
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;

  public MemberResponseDto signup(MemberRequestDto requestDto) {
    if (memberRepository.existsByEmail(requestDto.getEmail())) {
      throw new RuntimeException("이미 가입되어 있는 유저입니다");
    }

    Member member = requestDto.toMember(passwordEncoder);
    return MemberResponseDto.of(memberRepository.save(member));
  }

  public TokenDto login(MemberRequestDto requestDto) {
    UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

    Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

    return tokenProvider.generateTokenDto(authentication);
  }

  public List<Member> saveUser(int count) {
    List<Member> members = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Member member = Member.builder()
              .email("testAccount" + i)
              .password("1234")
              .name("tester" + i)
              .authority(Authority.ROLE_USER)
              .joinDate(LocalDateTime.now())
              .openStatus(OpenStatus.PUBLIC)
              .theme(Theme.LIGHT_MODE)
              .build();
      members.add(member);
      log.info("count : " + i);
    }
    memberRepository.saveAll(members);
    return members;
  }

  private final MyFlowRepository myFlowRepository;
  public void saveFlow(int count) {
    for(int i = 0; i < count; i++) {
      TimeLine timeLine = new TimeLine();
      LocalDateTime localDateTime = LocalDateTime.now();
      Member member = memberRepository.findByEmail("testAccount0");
      member.getTimeLineList().add(timeLine);
      timeLine.setMember(member);
      timeLine.setId((long) (i+1));
      timeLine.setLat((double) 0);
      timeLine.setLng((double) 0);
      timeLine.setTl_profile_pic("https://firebasestorage.googleapis.com/v0/b/spotflow-5475a.appspot.com/o/KakaoTalk_20230703_002523159.png?alt=media&token=8373de55-19f8-490e-a80c-487441b497e");
      timeLine.setContent("마이플로우샘플입니다마이플로우샘플입니다마이플로우샘플입니다마이플로우샘플입니다");
      timeLine.setPlace("홍대" + i);
      timeLine.setJoinDate(localDateTime);
      timeLine.setView(0);

      myFlowRepository.save(timeLine);
    }
  }
}

package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.constant.Authority;
import com.kh.project.spotflow.model.constant.OpenStatus;
import com.kh.project.spotflow.model.constant.Theme;
import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository repository;

  public List<Member> findAll() {
    return repository.findAll();
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

    return members;
  }
}

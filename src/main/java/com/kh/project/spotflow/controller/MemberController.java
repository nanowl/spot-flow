package com.kh.project.spotflow.controller;


import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class MemberController {
  private final MemberService service;
  @GetMapping("/list")
  public ResponseEntity<List<Member>> searchUser() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }
  @PostMapping("/dummy")
  public void addUser(@RequestBody Map<String, Object> request) {
    int count = (int) request.get("count");
    log.info(count + "명의 유저를 만듭니다.");
    service.saveUser(count);
  }
}

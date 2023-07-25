package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.service.DummyTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/dummy")
@RequiredArgsConstructor
@Slf4j
public class DummyTestController {
     private final DummyTestService dummyTestService;
     
     @PostMapping("/insertuser")
     public ResponseEntity<List<Customer>> saveDummyUser(@RequestBody int count) {
          List<Customer> customers = dummyTestService.saveDummyUser(count);
          return new ResponseEntity<>(customers, HttpStatus.OK);
     }
     

}

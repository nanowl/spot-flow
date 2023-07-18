package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.CustomerDto;
import com.kh.project.spotflow.model.dto.CustomerUpdateDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
     private final CustomerService customerService;
     
     // customer 정보 가죠오기
     @PostMapping("/profile")
     public ResponseEntity<CustomerDto> getCustomerProfile(HttpServletRequest request) {
          CustomerDto customerDto = customerService.getCustomerInfo(request);
          if(customerDto != null) return new ResponseEntity<>(customerDto, HttpStatus.OK);
          else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
     
     // customer 프로필 수정(사진, 상테메시지)
     @PostMapping("/updateprofile")
     public ResponseEntity<CustomerDto> updateProfile(HttpServletRequest request, @RequestBody CustomerUpdateDto customerUpdateDto) {
          return ResponseEntity.ok(customerService.updateProfile(request, customerUpdateDto));
     }
}

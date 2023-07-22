package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.CustomerRequestDto;
import com.kh.project.spotflow.model.dto.CustomerUpdateDto;
import com.kh.project.spotflow.model.dto.CustomerUserRequestDto;
import com.kh.project.spotflow.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
     private final CustomerService customerService;
     
     // customer 정보 가죠오기
     @GetMapping("/profile")
     public ResponseEntity<Map<String, Object>> getCustomerProfile() {
          Map<String,Object> customerData = customerService.getCustomerInfo();
          if (customerData != null) return new ResponseEntity<>(customerData, HttpStatus.OK);
          else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
     
     // customer 상테메시지 수정
     @PutMapping("/updatestatmsg")
     public ResponseEntity<CustomerUserRequestDto> updateCustomerStatMsg(@RequestBody CustomerUpdateDto customerUpdateDto) {
          String statMsg = customerUpdateDto.getStatMsg();
          CustomerUserRequestDto customerUserRequestDto = customerService.updateStatMsg(statMsg);
          if(statMsg != null) return new ResponseEntity<>(customerUserRequestDto, HttpStatus.OK);
          else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
     
     // customer 프로필 사진 수정
     @PutMapping("/updateprofilepic")
     public ResponseEntity<CustomerUserRequestDto> updateCustomerProfilePic(@RequestBody CustomerUpdateDto customerUpdateDto) {
          String img = customerUpdateDto.getProfilePic();
          CustomerUserRequestDto customerUserRequestDto = customerService.updateProfilePic(img);
          if(img != null) return new ResponseEntity<>(customerUserRequestDto, HttpStatus.OK);
          else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
}

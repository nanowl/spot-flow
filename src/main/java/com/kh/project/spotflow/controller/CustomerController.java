package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.CustomerDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.service.AuthService;
import com.kh.project.spotflow.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
     private final CustomerService customerService;
     
     @PostMapping("/profile")
     public ResponseEntity<CustomerDto> getCustomerProfile(HttpServletRequest request) {
          CustomerDto customerDto = customerService.getCustomerInfo(request);
          if(customerDto != null) return new ResponseEntity<>(customerDto, HttpStatus.OK);
          else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
}

package com.kh.project.spotflow.service;

import com.kh.project.spotflow.config.jwt.TokenProvider;
import com.kh.project.spotflow.model.dto.CustomerDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
     private final CustomerRepository customerRepository;
     private final AuthService authService;
     
     
     public CustomerDto getCustomerInfo(HttpServletRequest request) {
          Customer customer = authService.validateTokenGetCustomerInfo(request);
          CustomerDto customerDto = CustomerDto.getCustomerInfo(customer);
          return customerDto;
     }
     
}

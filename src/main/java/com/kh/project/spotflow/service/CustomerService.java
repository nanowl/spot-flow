package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.CustomerDto;
import com.kh.project.spotflow.model.dto.CustomerUpdateDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
     private final CustomerRepository customerRepository;
     private final AuthService authService;
     
     //회원 정보 조회
     public CustomerDto getCustomerInfo(HttpServletRequest request) {
          Customer customer = authService.validateTokenGetCustomerInfo(request);
          return CustomerDto.getCustomerInfo(customer);
     }
     
     //프로필 사진 수정
     @Transactional
     public CustomerDto updateProfile(HttpServletRequest request, CustomerUpdateDto customerUpdateDto) {
          Customer customer = authService.validateTokenGetCustomerInfo(request);
          if (customerUpdateDto.getStatMsg() == null) {
               customer.setProfilePic(customerUpdateDto.getProfilePic());
               customerRepository.save(customer);
          } else if (customerUpdateDto.getProfilePic() == null) {
               customer.setStatMsg(customerUpdateDto.getStatMsg());
               customerRepository.save(customer);
               
          } else {
               customer.setProfilePic(customerUpdateDto.getProfilePic());
               customer.setStatMsg(customerUpdateDto.getStatMsg());
               customerRepository.save(customer);
          }
          return CustomerDto.getCustomerInfo(customer);
     }
}

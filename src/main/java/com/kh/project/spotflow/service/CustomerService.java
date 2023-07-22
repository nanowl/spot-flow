package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.CustomerUserRequestDto;
import com.kh.project.spotflow.model.dto.FollowUserRequestDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
     private final CustomerRepository customerRepository;
     private final AuthService authService;
     private final FollowService followService;
     
     //회원 정보 조회
     public Map<String, Object> getCustomerInfo() {
          Customer customer = authService.getCustomerByEmail();
          Map<String, Object> customerData = new HashMap<>();
          FollowUserRequestDto followUserRequestDto = new FollowUserRequestDto();
          followUserRequestDto.setFollower(followService.getFollower(customer));
          followUserRequestDto.setFollowing(followService.getFollowing(customer));
          customerData.put("customer", CustomerUserRequestDto.getCustomerInfo(customer));
          customerData.put("follower", followUserRequestDto);
          return customerData;
     }
     
     //회원 상테메시지 수정
     public CustomerUserRequestDto updateStatMsg(String msg) {
          Customer customer = authService.getCustomerByEmail();
          customer.setStatMsg(msg);
          customerRepository.save(customer);
          return CustomerUserRequestDto.getCustomerInfo(customer);
     }
     
     //회원 프로필 사진 수정
     public CustomerUserRequestDto updateProfilePic(String img) {
          Customer customer = authService.getCustomerByEmail();
          customer.setProfilePic(img);
          customerRepository.save(customer);
          return CustomerUserRequestDto.getCustomerInfo(customer);
     }
}

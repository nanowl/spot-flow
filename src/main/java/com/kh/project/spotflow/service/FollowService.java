package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FollowService {
     private final AuthService authService;
     private final CustomerRepository customerRepository;
     private final FollowRepository followRepository;
     
     // 팔로워 수 (follower)
     public Long getFollower(Customer customer) {
          return followRepository.countByFollower(customer);
     }
     // 팔로잉 수 (following)
     public Long getFollowing(Customer customer){
          return followRepository.countByFollowing(customer);
     }
}

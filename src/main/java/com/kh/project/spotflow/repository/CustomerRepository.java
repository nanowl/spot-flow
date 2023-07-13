package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  //이게 뭐지??????
  Optional<Customer> findByEmail(String email);
  Customer findCustomerByEmail(String email);
  // 이메일 중복 확인
  boolean existsByEmail(String email);
  
  // 닉네임 중복 확인
  boolean existsByNickName(String nickName);
  
  
}


package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
 
  // 이메일 로 회원 정보 추출 하기 UserDetails 로 필요함
  Optional<Customer> findByEmail(String email);
  
  //Dummy test 사용중 나중에 테스트 끝나면 지울 예정
  Customer findCustomerByEmail(String email);
  
  // 이메일 중복 확인
  boolean existsByEmail(String email);
  
  // 닉네임 중복 확인
  boolean existsByNickName(String nickName);
  
}


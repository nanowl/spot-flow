package com.kh.project.spotflow.model.dto;


import com.kh.project.spotflow.model.entity.Customer;
import lombok.*;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CustomerResponseDto {
  private String email;
  private String nickName;
  
  public static CustomerResponseDto of(Customer customer) {
    return CustomerResponseDto.builder()
            .email(customer.getEmail())
            .nickName(customer.getNickName())
            .build();
  }
}

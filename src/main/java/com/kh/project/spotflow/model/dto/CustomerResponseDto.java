package com.kh.project.spotflow.model.dto;


import com.kh.project.spotflow.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CustomerResponseDto {
  private String email;
  private String name;
  
  public static CustomerResponseDto of(Customer customer) {
    return CustomerResponseDto.builder()
            .email(customer.getEmail())
            .name(customer.getName())
            .build();
  }
}

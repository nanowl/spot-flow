package com.kh.project.spotflow.model.dto;

import com.kh.project.spotflow.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CustomerDto {
     private String email;
     private String nickName;
     private String statMsg;
     private String profilePic;
     
     public static CustomerDto getCustomerInfo(Customer customer) {
          return CustomerDto.builder()
            .email(customer.getEmail())
            .nickName(customer.getNickName())
            .statMsg(customer.getStatMsg())
            .profilePic(customer.getProfilePic())
            .build();
     }
}

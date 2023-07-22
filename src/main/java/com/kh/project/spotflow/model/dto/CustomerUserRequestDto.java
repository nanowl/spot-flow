package com.kh.project.spotflow.model.dto;

import com.kh.project.spotflow.model.constant.Theme;
import com.kh.project.spotflow.model.entity.Customer;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CustomerUserRequestDto {
     // 팔요한 회원정보만 사용하는 DTO following follower 추가 필요
     private String email;
     private String nickName;
     private String statMsg;
     private String profilePic;
     private Theme theme;
     
     public static CustomerUserRequestDto getCustomerInfo(Customer customer) {
          return CustomerUserRequestDto.builder()
            .email(customer.getEmail())
            .nickName(customer.getNickName())
            .statMsg(customer.getStatMsg())
            .profilePic(customer.getProfilePic())
            .theme(customer.getTheme())
            .build();
     }
}

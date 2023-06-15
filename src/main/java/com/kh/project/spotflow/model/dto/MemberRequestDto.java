package com.kh.project.spotflow.model.dto;

import com.kh.project.spotflow.model.constant.Authority;
import com.kh.project.spotflow.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MemberRequestDto {
  private String email;
  private String password;

  public Member toMember(PasswordEncoder passwordEncoder) {
    return Member.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .authority(Authority.ROLE_USER)
            .build();
  }
  public UsernamePasswordAuthenticationToken toAuthentication() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}

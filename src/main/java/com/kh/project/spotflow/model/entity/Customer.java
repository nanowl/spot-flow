package com.kh.project.spotflow.model.entity;

import com.kh.project.spotflow.model.constant.Authority;
import com.kh.project.spotflow.model.constant.OpenStatus;
import com.kh.project.spotflow.model.constant.Theme;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter @Setter @ToString
@NoArgsConstructor
@Component
public class Customer {

  @Id
  @Column(name = "ct_email")
  private String email;

  @Column(name = "ct_name")
  private String name;

  @Column(name = "ct_nick_name")
  private String nickName;

  @Column(name = "ct_pwd")
  private String password;

  @Column(name = "ct_stat_msg")
  private String statMsg;

  @Column(name = "ct_profile_pic")
  private String profilePic; // 프로필 사진이 저장된 위치를 저장

  @Column(name = "ct_join_date")
  private LocalDateTime joinDate;

  @Column(name = "ct_update")
  private LocalDateTime updateTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "ct_open_stat")
  private OpenStatus openStatus;

  @Enumerated(EnumType.STRING)
  @Column(name = "ct_theme")
  private Theme theme;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "ct_authority")
  private Authority authority;

  @Builder
  public Customer(String email, String password, String profilePic ,String name, Authority authority, LocalDateTime joinDate) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.profilePic = profilePic;
    this.authority = authority;
    this.joinDate = joinDate;
    this.openStatus = openStatus;
    this.theme = theme;
    this.profilePic = profilePic;
    
  }
}

package com.kh.project.spotflow.model.entity;

import com.kh.project.spotflow.model.constant.Authority;
import com.kh.project.spotflow.model.constant.OpenStatus;
import com.kh.project.spotflow.model.constant.Theme;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter @Setter @ToString
@NoArgsConstructor
@Component
public class Member {

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


  @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
  private List<TimeLine> timeLineList = new ArrayList<>();

  @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
  private List<Diary> diaryList = new ArrayList<>();

  @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
  private List<DiaryComment> commentList = new ArrayList<>();

  @OneToMany(mappedBy = "follower" , cascade = CascadeType.ALL)
  private List<Follow> followerList = new ArrayList<>();

  @OneToMany(mappedBy = "following" , cascade = CascadeType.ALL)
  private List<Follow> followingList = new ArrayList<>();

  @Builder
  public Member(String email, String password, String name, Authority authority,
                LocalDateTime joinDate, OpenStatus openStatus, Theme theme, String profilePic) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.authority = authority;
    this.joinDate = joinDate;
    this.openStatus = openStatus;
    this.theme = theme;
    this.profilePic = profilePic;
  }





}

package com.kh.project.spotflow.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {
  private int id;
  private String user;
  private String pwd;
  private String name;
  private String email;
  private String profilePic;

}

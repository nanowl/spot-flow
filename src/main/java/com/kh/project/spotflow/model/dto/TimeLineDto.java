package com.kh.project.spotflow.model.dto;

import com.kh.project.spotflow.model.entity.TimeLine;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeLineDto {
    // 사용자 닉네임 , 날짜
    private String content;
    private int view;
    private  String tl_profile_pic;
    private String place;
    private LocalDateTime updateTime;
    private MemberDto member;
    private String title;



}

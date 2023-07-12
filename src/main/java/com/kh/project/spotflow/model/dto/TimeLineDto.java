package com.kh.project.spotflow.model.dto;

import com.kh.project.spotflow.model.entity.TimeLine;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeLineDto {
    private long id;
    private String tl_profile_pic;
    private int view;
    private String content;
    private LocalDateTime updateTime;
    private String nickName;
    private String ct_profile_pic;


}

package com.kh.project.spotflow.model.dto.TimeLine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeLineRequestDto {
     private String tl_profile_pic;
     private String content;
     private String place;
     private double lat;
     private double lng;
     
}
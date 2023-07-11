package com.kh.project.spotflow.model.dto;

import com.kh.project.spotflow.model.entity.TimeLine;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeLineDto {
    // 사용자 닉네임 , 날짜
    private String userEmail;
     private String content;  // 타임라인 글내용
    private int view;       // 조회수
    private String tl_profile_pic;  // 타임라인 사진
    private String place;       // 장소명
    private LocalDateTime updateTime; // 수정시간
    private MemberDto member; //
    private double lat ; // 위도
    private double lng ; // 경도
    private String title;   //  글제목 필요없음현재

    public TimeLine toEntity() {
        return TimeLine.builder()
                .place(this.place)
                .content(this.content)
                .joinDate(this.updateTime)
                .view(this.view)
                .title(this.title)
                .image(this.tl_profile_pic)
                .build();
    }


}

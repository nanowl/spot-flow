
package com.kh.project.spotflow.model.dto;

import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TimeLineDto {
    private long id;
    private String tl_profile_pic;
    private int view;
    private String content;
    private LocalDateTime updateTime;
    private String nickName;
    private String ct_profile_pic;
    private Customer customer;
    private String email;
    private String place;


    public static TimeLineDto timeLineDto(TimeLine timeLine) {
        return TimeLineDto.builder()
                .nickName(timeLine.getCustomer().getNickName())
                .tl_profile_pic(timeLine.getImage())
                .place(timeLine.getPlace())
                .content(timeLine.getContent())
                .ct_profile_pic(timeLine.getCustomer().getProfilePic())
                .updateTime(timeLine.getJoinDate())
                .view(timeLine.getView())
                .id(timeLine.getId())
                .build();
    }
}


package com.kh.project.spotflow.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kh.project.spotflow.model.dto.diary.DiaryResponseDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryComment;
import com.kh.project.spotflow.model.entity.Notification;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseNotification {
    private String diaryWriter;
    private String diary;
    private String commentWriter;
    private String comment;
    private Long id;

    public ResponseNotification of(Notification notification) {
        return ResponseNotification.builder()
                .id(notification.getId())
                .diaryWriter(notification.getDiaryWriter().getNickName())
                .diary(notification.getDiary().getTitle())
                .commentWriter(notification.getDiaryComment().getCustomer().getNickName())
                .comment(notification.getDiaryComment().getContent())
                .build();
    }

}

package com.kh.project.spotflow.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kh.project.spotflow.model.dto.diary.DiaryResponseDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryComment;
import com.kh.project.spotflow.model.entity.Notification;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class ResponseNotification {
    private String diaryWriter;
    private String diary;
    private String commentWriter;
    private String comment;
    private Long id;
    private boolean isRead;

    public ResponseNotification of(Notification notification) {
        log.info(notification.getDiaryWriter().getNickName());
        log.info(notification.getDiaryComment().getCustomer().getNickName());
        log.info(notification.getDiary().getTitle());
        return ResponseNotification.builder()
                .id(notification.getId())
                .diaryWriter(notification.getDiaryWriter().getNickName())
                .diary(notification.getDiary().getTitle())
                .commentWriter(notification.getDiaryComment().getCustomer().getNickName())
                .comment(notification.getDiaryComment().getContent())
                .isRead(notification.isRead())
                .build();
    }

}

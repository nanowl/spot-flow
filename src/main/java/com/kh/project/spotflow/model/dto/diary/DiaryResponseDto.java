package com.kh.project.spotflow.model.dto.diary;

import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryItem;
import com.kh.project.spotflow.model.entity.TimeLine;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DiaryResponseDto {
  private String title;
  private String content;
  private Customer customer; // 다이어리 게시자
  private LocalDateTime joinDate;
  private LocalDateTime updateTime;
  private Integer like;
  private Integer view;
  private boolean isDelete;
  private List<TimeLine> timeLineList;
  private List<DiaryItem> itemList;

  public DiaryResponseDto of(Diary diary) {
    return DiaryResponseDto.builder()
            .title(diary.getTitle())
            .content(diary.getContent())
            .customer(diary.getCustomer())
            .joinDate(diary.getJoinDate())
            .updateTime(diary.getUpdateTime())
            .like(diary.getLike())
            .view(diary.getView())
            .isDelete(diary.isDelete())
            .build();
  }
}

package com.kh.project.spotflow.model.dto;

import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.TimeLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DiaryRequestDto {
  private String title;
  private String content;
  private String email; // 다이어리 게시자
  private List<TimeLine> timeLineList;

  public Diary toDiary() {
    return Diary.builder()
            .title(this.title)
            .content(this.content)
            .joinDate(LocalDateTime.now())
            .like(0)
            .view(0)
            .build();
  }
}

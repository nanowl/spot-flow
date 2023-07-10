package com.kh.project.spotflow.model.dto.diary;

import com.kh.project.spotflow.model.dto.TimeLineRequestDto;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.TimeLine;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DiaryRequestDto {
  private String title;
  private String content;
  private String email; // 다이어리 게시자
  private List<TimeLineRequestDto> timeLineList;

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

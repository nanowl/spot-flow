package com.kh.project.spotflow.model.dto.diary.request;

import com.kh.project.spotflow.model.dto.diary.TimeLineRequestDto;
import com.kh.project.spotflow.model.entity.Diary;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DiaryCreateRequest {
  private Long id;
  private String title;
  private String content;
  private String email; // 다이어리 게시자
  private List<TimeLineRequestDto> timeLineList;

  public Diary toDiary() {
    return Diary.builder()
            .title(this.title)
            .content(this.content)
            .joinDate(LocalDateTime.now())
            .view(0)
            .isDelete(false)
            .build();
  }
}

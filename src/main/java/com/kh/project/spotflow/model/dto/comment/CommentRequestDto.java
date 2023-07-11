package com.kh.project.spotflow.model.dto.comment;

import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryComment;
import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CommentRequestDto {
  private int diary;
  private String content;
  private String email; // 댓글 게시자

  public DiaryComment toComment(Member member, Diary diary) {
    return DiaryComment.builder()
            .diary(diary)
            .member(member)
            .content(this.content)
            .joinDate(LocalDateTime.now())
            .build();
  }
}

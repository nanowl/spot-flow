package com.kh.project.spotflow.model.dto.diary;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryResponseAllDto {
     private Long id;
     private String title;
     private String nickname;
     private String profilePic;
     private Long like;
     private List<String> img;
}

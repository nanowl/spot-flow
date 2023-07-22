package com.kh.project.spotflow.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowUserRequestDto {
     private long follower;
     private long following;
     
}

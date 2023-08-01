package com.kh.project.spotflow.model.dto.Follow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowingUserDto {
     private String email;
     private String profilePic;
     private String nickname;
}

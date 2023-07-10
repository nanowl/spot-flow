package com.kh.project.spotflow.model.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follow")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
public class Follow {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "follower")
  private Customer follower; // 팔로우 기능을 실행한 사람

  @ManyToOne
  @JoinColumn(name = "following")
  private Customer following; // 팔로우하는 대상

  @Column(name = "follow_join_date")
  private LocalDateTime joinDate;

  @Builder
  public Follow(Customer toUser, Customer fromUser, LocalDateTime joinDate){
    this.follower = toUser;
    this.following = fromUser;
    this.joinDate = joinDate;
  }


}

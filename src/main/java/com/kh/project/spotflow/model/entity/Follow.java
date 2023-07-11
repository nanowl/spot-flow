package com.kh.project.spotflow.model.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follow")
public class Follow {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @Override
  public String toString() {
    return "Follow{" +
            "id=" + id +
            ", follower=" + follower +
            ", following=" + following +
            ", joinDate=" + joinDate +
            '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Member getFollower() {
    return follower;
  }

  public void setFollower(Member follower) {
    this.follower = follower;
  }

  public Member getFollowing() {
    return following;
  }

  public void setFollowing(Member following) {
    this.following = following;
  }

  public LocalDateTime getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(LocalDateTime joinDate) {
    this.joinDate = joinDate;
  }
}


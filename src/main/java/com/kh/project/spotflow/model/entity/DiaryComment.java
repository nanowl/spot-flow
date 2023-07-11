package com.kh.project.spotflow.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diary_comment")
@Getter @Setter
@NoArgsConstructor
public class DiaryComment {
  @Id
  @Column(name = "cm_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cm_customer")
  @JsonManagedReference
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "cm_diary")
  @JsonManagedReference
  private Diary diary;

  @Column(name = "cm_content", nullable = false, length = 512)
  private String content;

  @Column(name = "cm_join_date")
  private LocalDateTime joinDate;

  @Column(name = "cm_update")
  private LocalDateTime update;

  @Builder
  public DiaryComment(Customer customer, Diary diary, String content, LocalDateTime joinDate) {
    this.customer = customer;
    this.diary = diary;
    this.content = content;
    this.joinDate = joinDate;
  }
}

package com.kh.project.spotflow.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diary_comment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
public class DiaryComment {
  @Id
  @Column(name = "cm_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cm_customer")
  private Member email;

  @ManyToOne
  @JoinColumn(name = "cm_diary")
  private Diary diary;

  @Column(name = "cm_content", nullable = false, length = 512)
  private String content;

  @Column(name = "cm_join_date")
  private LocalDateTime joinDate;

  @Column(name = "cm_update")
  private LocalDateTime update;
}

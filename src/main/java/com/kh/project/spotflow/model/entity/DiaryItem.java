package com.kh.project.spotflow.model.entity;

import javax.persistence.*;

@Entity
public class DiaryItem {
  @Id
  @Column(name = "mapper_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "mapper_diary")
  private Diary diary;

  @ManyToOne
  @JoinColumn(name ="mapper_time_line")
  private TimeLine timeLine;
}

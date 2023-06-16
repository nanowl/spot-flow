package com.kh.project.spotflow.model.entity;

import javax.persistence.*;

@Entity
public class DiaryItem {
  @Id
  @Column(name = "mapper_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "di_id")
  private Diary diary;

  @ManyToOne
  @JoinColumn(name ="tl_id")
  private TimeLine timeLine;
}

package com.kh.project.spotflow.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "timeline")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
public class TimeLine {
  @Id
  @Column(name = "tl_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ct_email")
  private Member email;

  @Column(name = "tl_category", nullable = false, length = 128)
  private String category;

  @Column(name = "tl_place", nullable = false, length = 128)
  private String place;

  @Column(name = "tl_title", nullable = false)
  private String title;

  @Column(name = "tl_profile_pic", nullable = false)
  private String tl_profile_pic;

  @Column(name = "tl_content", length = 512)
  private String content;

  @Column(name = "tl_lat")
//  @ColumnDefault("0")
  private Double lat;

  @Column(name = "tl_lng")
//  @ColumnDefault("0")
  private Double lng;

  @Column(name = "tl_join_date", nullable = false)
  private LocalDateTime joinDate;

  @Column(name = "tl_update")
  private LocalDateTime updateTime;

  @Column(name = "tl_view", nullable = false, length = 128)
  @ColumnDefault("0")
  private Integer view;

//  @OneToMany(mappedBy = "timeline")
//  private List<DiaryItem> diaryItemList = new ArrayList<>();
}

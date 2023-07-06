package com.kh.project.spotflow.model.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "diary")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
public class Diary {
  @Id
  @Column(name = "di_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "di_customer")
  private Member member;

  @Column(name = "di_category", nullable = false, length = 128)
  private String category;

  @Column(name = "di_title", nullable = false)
  private String title;

  @Column(name = "di_content", length = 512)
  private String content;

  @Column(name = "di_join_date", nullable = false)
  private LocalDateTime joinDate;

  @Column(name = "di_update")
  private LocalDateTime updateTime;

  @Column(name = "di_like")
  private Integer like;

  @Column(name = "di_view")
  private Integer view;

  @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
  private List<DiaryComment> commentList;

  @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
  private List<DiaryItem> itemList;

//  @OneToMany(mappedBy = "diary")
//  private List<DiaryItem> diaryItemList = new ArrayList<>();

}

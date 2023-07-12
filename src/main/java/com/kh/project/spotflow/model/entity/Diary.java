package com.kh.project.spotflow.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "diary")
@NoArgsConstructor
@Getter
@Setter
public class Diary {
  @Id
  @Column(name = "di_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "di_customer")
  private Customer customer;

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

  @Column(name = "di_isDelete")
  @ColumnDefault("FALSE")
  private boolean isDelete;

  @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<DiaryItem> itemList;

  @Builder
  public Diary (String title, String content, LocalDateTime joinDate,
                Integer like, Integer view, boolean isDelete) {
    this.title = title;
    this.content = content;
    this.joinDate = joinDate;
    this.like = like;
    this.view = view;
    this.isDelete = isDelete;
  }

  public boolean isDelete() {
    return isDelete;
  }
}

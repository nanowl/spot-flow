package com.kh.project.spotflow.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "timeline")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Getter @Setter
public class TimeLine {
  @Id
  @Column(name = "tl_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "tl_customer")
  private Customer customer;

  @Column(name = "tl_place", nullable = false, length = 128)
  private String place;

  @Column(name = "tl_title", nullable = false)
  private String title;

  @Column(name = "tl_profile_pic", nullable = false, columnDefinition = "LONGTEXT")
  private String image;

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



  @OneToMany(mappedBy = "timeLine")
  private List<DiaryItem> itemList = new ArrayList<>();

  @Builder
  public TimeLine(Member member, String place, String tl_profile_pic, String content, Double lat, Double lng, LocalDateTime joinDate, Integer view) {
    this.member = member;
    this.place = place;
    this.tl_profile_pic = tl_profile_pic;
    this.content = content;
    this.lat = lat;
    this.lng = lng;
    this.joinDate = joinDate;
    this.view = view;
  }
  @OneToMany(mappedBy = "timeLine",cascade = CascadeType.ALL)
  @JsonBackReference
  private List<DiaryItem> itemList;
}

package com.kh.project.spotflow.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "chat_log")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ChatLog {
  @Id
  @Column(name = "chat_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "sender")
  private Customer sender;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "room")
  private ChatRoom chatRoom;

  @Column(name = "content", columnDefinition = "LONGTEXT")
  private String content;

  private boolean isDelete;

  @Column(name = "send_date")
  private LocalDateTime date;
}

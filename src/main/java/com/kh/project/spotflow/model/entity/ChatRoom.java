package com.kh.project.spotflow.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "chat_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {

  @Id
  @Column(name = "room_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "room_name")
  private String RoomName;

  @Column(name = "start_date")
  private LocalDateTime date;

  @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
  @JsonBackReference
  List<ChatLog> chatLogList;
}

package com.kh.project.spotflow.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
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

  @OneToMany(mappedBy = "chatRoom")
  @JsonBackReference
  List<ChatLog> chatLogList;
}

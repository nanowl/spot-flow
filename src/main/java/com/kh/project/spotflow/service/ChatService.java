package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.chat.ChatRoomDto;
import com.kh.project.spotflow.model.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
  private final AuthService authService;

  // 유저 별 모든 채팅방 검색
  public List<ChatRoomDto> findAllRoom() {
    Customer user = authService.getCustomerByEmail();

    return new ArrayList<>();
  }

  // 유니크 값으로 채팅방 검색
  public ChatRoomDto findRoomById(String roomId) {
    return new ChatRoomDto();
  }

  // 방 개설하기
  public ChatRoomDto createRoom(String name) {
    return new ChatRoomDto();
  }
}
package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.chat.ChatMessage;
import com.kh.project.spotflow.model.entity.ChatLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
  private final AuthService authService;

  @Transactional
  public ChatLog sendChat(ChatMessage message) {
    ChatLog chatLog = ChatLog.builder().build();

    return chatLog;
  }

}
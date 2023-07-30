package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.model.dto.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {
  private final SimpMessagingTemplate template;

  @MessageMapping("/message")
  public void greet(@Payload ChatMessage message) {
    log.info("매핑 테스트입니다.");
    log.info(message.toString());
    template.convertAndSend("/notification/message" + message.getSender(), message);
  }
}
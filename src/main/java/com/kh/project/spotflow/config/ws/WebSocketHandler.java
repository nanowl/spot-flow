//package com.kh.project.spotflow.config.ws;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kh.project.spotflow.model.dto.chat.ChatMessage;
//import com.kh.project.spotflow.model.dto.chat.ChatRoom;
//import com.kh.project.spotflow.service.ChatService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@RequiredArgsConstructor
//@Slf4j
//@Component
//public class WebSocketHandler extends TextWebSocketHandler {
//  private final ObjectMapper objectMapper;
//  private final ChatService chatService;
//  @Override
//  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//    String payload = message.getPayload();
//    log.warn("{}", payload);
//    ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
//    ChatRoom chatRoom = chatService.findRoomById(chatMessage.getRoomId());
//    chatRoom.handlerActions(session, chatMessage, chatService);
//  }
//}

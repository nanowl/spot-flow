package com.kh.project.spotflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send-message/{email}")
    public void sendMessageToUser(@Payload String message, @DestinationVariable String email) {
        // 이메일로 식별된 개인에게 메시지를 전송합니다.
        log.info("매핑 테스트" + email);
        messagingTemplate.convertAndSend("/region/" + email, message);

    }
}

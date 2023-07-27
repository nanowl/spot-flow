package com.kh.project.spotflow.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/app/sendMessage/{email}")
    public void sendMessageToUser(@Payload String message, @DestinationVariable String email) {
        // 이메일로 식별된 개인에게 메시지를 전송합니다.
        messagingTemplate.convertAndSend("/notification/" + email, "Hello, " + email + "! You sent: " + message);
    }
}

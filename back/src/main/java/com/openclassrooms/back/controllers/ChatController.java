package com.openclassrooms.back.controllers;

import java.time.Instant;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.openclassrooms.back.model.ChatMessage;

@Controller
public class ChatController {

  @MessageMapping("/chat.send")
  @SendTo("/topic/messages")
  public ChatMessage send(ChatMessage message) {
    message.setTimestamp(Instant.now().toString());
    return message;
  }
}
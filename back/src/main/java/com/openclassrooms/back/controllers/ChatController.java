package com.openclassrooms.back.controllers;

import java.security.Principal;
import java.time.Instant;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.openclassrooms.back.model.ChatMessage;

@Controller
public class ChatController {

  @MessageMapping("/chat.send")
  @SendTo("/topic/messages")
  public ChatMessage send(ChatMessage message, Principal principal) {
	  message.setSender(principal.getName());
    message.setTimestamp(Instant.now().toString());
    return message;
  }
}
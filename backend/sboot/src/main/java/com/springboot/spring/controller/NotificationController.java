package com.springboot.spring.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {
    
    @MessageMapping("/notifyChanges")
    @SendTo("/topic/changes")
    public String notifyChanges(String message) {
        return message;
    }
}
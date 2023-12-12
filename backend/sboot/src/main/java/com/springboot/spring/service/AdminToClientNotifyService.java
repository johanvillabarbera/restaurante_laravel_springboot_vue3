package com.springboot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminToClientNotifyService {
    
    @Autowired
    private SimpMessagingTemplate template;

    public void NotifyMenus() {
        template.convertAndSend("/topic", "Menú creado");
    }

    public void NotifyTables() {
        template.convertAndSend("/topic", "Table creado");
    }
    
}
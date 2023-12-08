package com.springboot.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.springboot.spring.service.AdminToClientNotifyService;

import org.springframework.messaging.handler.annotation.SendTo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notify")
public class AdminToClientNotifyController {

    @Autowired
    AdminToClientNotifyService adminToClientNotifyService;

    @GetMapping("/menus")
    public void notifyMenus() {
        adminToClientNotifyService.NotifyMenus();
    }

    @GetMapping("/tables")
    public void notifyTables() {
        adminToClientNotifyService.NotifyTables();
    }
}
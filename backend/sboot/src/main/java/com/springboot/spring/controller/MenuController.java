package com.springboot.spring.controller;

import com.springboot.spring.model.Menu;
import com.springboot.spring.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.springboot.spring.service.MenuService;

import org.springframework.messaging.handler.annotation.SendTo;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    MenuRepository menuRepository;
    
    // List of menus
    @GetMapping("")
    public ResponseEntity<List<Menu>> getAllMenus() {
        try {
            
            List<Menu> menus = new ArrayList<Menu>();

            menuRepository.findAll().forEach(menus::add);

            if (menus.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(menus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    MenuService menuService;

    @PostMapping("")
    //@SendTo("/topic/user")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        try {
            Menu createdMenu = menuService.createMenu(menu);
            return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
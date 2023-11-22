package com.springboot.spring.service;

import com.springboot.spring.model.Menu;
import com.springboot.spring.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springboot.spring.config.WebSocketConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    public Menu createMenu(Menu menu) {
        Menu savedMenu = menuRepository.save(menu);
        template.convertAndSend("/topic", "Menú creado");
        return savedMenu;
    }

    public Menu updateMenu(Long id, Menu updatedMenu, SimpMessagingTemplate template) {
        Menu existingMenu = menuRepository.findById(id).orElse(null);
        if (existingMenu != null) {
            existingMenu.setName(updatedMenu.getName());
            existingMenu.setDescription(updatedMenu.getDescription());
            // Actualizar otras propiedades según sea necesario
            return menuRepository.save(existingMenu);
        }
        return null;
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}

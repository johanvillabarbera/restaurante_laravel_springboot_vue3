// package com.springboot.spring.service;

// import com.springboot.spring.model.Menu;
// import com.springboot.spring.repository.MenuRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// // import org.springframework.messaging.simp.SimpMessagingTemplate;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class MenuService {

//     private final MenuRepository menuRepository;
//     private final List<WebSocketSession> sessions; // Lista de sesiones WebSocket
//     //private final SimpMessagingTemplate messagingTemplate;

//     @Autowired
//     public MenuService(MenuRepository menuRepository) {
//         this.menuRepository = menuRepository;
//         //this.messagingTemplate = messagingTemplate;
//         this.sessions = new ArrayList<>();
//     }

//     public List<Menu> getAllMenus() {
//         return menuRepository.findAll();
//     }

//     public Menu getMenuById(Long id) {
//         return menuRepository.findById(id).orElse(null);
//     }

//     public Menu createMenu(Menu menu) {
//         Menu savedMenu = menuRepository.save(menu);
//         notifyClients("Nuevo menú creado: " + savedMenu.getName());
//         return savedMenu;
//     }

//     public Menu updateMenu(Long id, Menu updatedMenu) {
//         Menu existingMenu = menuRepository.findById(id).orElse(null);
//         if (existingMenu != null) {
//             existingMenu.setName(updatedMenu.getName());
//             existingMenu.setDescription(updatedMenu.getDescription());
//             // Actualizar otras propiedades según sea necesario
//             return menuRepository.save(existingMenu);
//         }
//         return null;
//     }

//     public void deleteMenu(Long id) {
//         menuRepository.deleteById(id);
//     }
    
//     public void addSession(WebSocketSession session) {
//         sessions.add(session);
//     }
    
//     public void removeSession(WebSocketSession session) {
//         sessions.remove(session);
//     }
    
//     public void notifyClients(String message) {
//         for (WebSocketSession session : sessions) {
//             try {
//                 session.sendMessage(new TextMessage(message));
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }

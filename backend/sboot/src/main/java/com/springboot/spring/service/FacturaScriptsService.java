package com.springboot.spring.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;



@Service
public class FacturaScriptsService {

    public void altaCliente(String token, String codcliente, String cifnif, String nombrecliente, String surname, String email, String tlf, String username) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Token", token);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("codcliente", codcliente);
        map.add("cifnif", cifnif);
        map.add("nombre", nombrecliente + " " + surname);
        map.add("email", email);
        map.add("telefono1", tlf);
        map.add("observaciones", "Cliente creado desde la aplicación web de Bellidel "+ "Username: " + username);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "http://bellidel.eu:8081/api/3/clientes";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

    }

}
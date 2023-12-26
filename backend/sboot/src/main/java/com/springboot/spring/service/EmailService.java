package com.springboot.spring.service;

import com.springboot.spring.model.email.EmailConfirmBooking;
import com.springboot.spring.model.email.EmailDataConfirmBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.core.JsonProcessingException;


@Service
public class EmailService {
    
    private final RestTemplate restTemplate;
    private final String url = "http://bellidel.eu:3000/api/send_mail";

    @Autowired
    public EmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmailConfirmBooking(EmailConfirmBooking email) {
        
        try {
            // Convertir el objeto EmailConfirmBooking a JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonEmail = mapper.writeValueAsString(email);

            // Configurar headers HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Crear la entidad HTTP
            HttpEntity<String> request = new HttpEntity<>(jsonEmail, headers);
            // Print request
            System.out.println(request);
            
            // Enviar la petición POST
            restTemplate.postForObject(url, request, String.class);
        } catch (JsonProcessingException e) {
            // Manejar la excepción de procesamiento de JSON aquí
            e.printStackTrace();
        } catch (Exception e) {
            // Manejar otras excepciones aquí
            e.printStackTrace();
        }
    }
}
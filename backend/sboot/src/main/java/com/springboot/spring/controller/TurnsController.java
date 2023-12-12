package com.springboot.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springboot.spring.model.Turns;
import com.springboot.spring.repository.TurnsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@CrossOrigin(origins = "http://bellidel.eu:8000")
@RestController
@RequestMapping("/turns")
public class TurnsController {

    @Autowired
    TurnsRepository turnsRepository;

    @GetMapping("")
    public ResponseEntity<List<Turns>> getAllTurns() {
        try {
            List<Turns> turns = new ArrayList<Turns>();

            turnsRepository.findAll().forEach(turns::add);

            if (turns.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(turns, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
package com.springboot.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springboot.spring.model.User;
import com.springboot.spring.model.UserWithToken;
import com.springboot.spring.repository.UserRepository;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.springboot.spring.security.jwt.JwtUtils;

import org.springframework.security.crypto.password.PasswordEncoder;

@CrossOrigin(origins = "http://bellidel.eu:8000")
@RestController
@RequestMapping("/user")
public class UserController {

    // LOGS
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<User>();

            userRepository.findAll().forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserWithToken> loginUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            User user_ = userRepository.findByUsername(user.getUsername()).get();
            UserWithToken userToken = new UserWithToken(jwt, user_);

            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {

            if (userRepository.existsByUsername(user.getUsername()) > 0) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            
            if (userRepository.existsByEmail(user.getEmail()) > 0) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            if (userRepository.existsByTlf(user.getTlf()) > 0) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            user.setPassword(encoder.encode(user.getPassword()));
            user.setIDrol("1");
            User _user = userRepository.save(user);
            //User _user = userRepository.save(new User(user.getClientID(), user.getName(), user.getSurname(), user.getEmail(), user.setPassword(encoder.encode(user.getPassword())), user.getTlf(), user.getAddress(), user.getBirth_date(), user.getIDrol(), user.getUsername()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
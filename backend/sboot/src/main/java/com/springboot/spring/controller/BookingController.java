package com.springboot.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;
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
import com.springboot.spring.repository.UserEventTableRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.springboot.spring.security.jwt.AuthTokenFilter;
import com.springboot.spring.model.UserEventTable;
import java.util.Date;
import com.springboot.spring.model.Booking;
import com.springboot.spring.repository.BookingRepository;

@CrossOrigin(origins = "http://bellidel.eu:8000")
@RestController
@RequestMapping("/booking")
public class BookingController {

    // LOGS
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserEventTableRepository userEventTableRepository;

    @Autowired
    BookingRepository bookingRepository;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, HttpServletRequest request) {
        try {
            
            // Comprueba si hay body si no hay enviamos que faltan datos
            if (booking == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername()).get();

            // logger.info("UserDetails: " + user);

            // Comprueba si la mesa esta ocupada
            List<Booking> bookingList = bookingRepository.findBookingByBookingDayAndTurnIDAndTableID(booking.getBooking_day(), booking.getTurnID(), booking.getTableID());

            // logger.info("BookingList: " + bookingList);

            if (!bookingList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            // Setea bookingId como 0 y getstatus como 0
            booking.setBookingID(0);
            booking.setStatus(0);


            Booking _booking = bookingRepository.save(new Booking(booking.getBookingID(), user.getClientID(),
                    booking.getTableID(), booking.getTurnID(), booking.getBooking_day(), booking.getDiners_number(),
                    booking.getStatus()));
            
            // SAVE RESERVATION EVENT
            UserEventTable userEventTable = new UserEventTable();

             String ipAddress = request.getRemoteAddr(); // Obtains IP user
             String userAgent = request.getHeader("User-Agent"); // Obtains User Agent

            userEventTable.setUserID(user.getClientID());
            userEventTable.setEventType("RESERVATION");
            userEventTable.setEventTimestamp(new Date());
            userEventTable.setUserIP(ipAddress);
            userEventTable.setEventDetails("Reservation");
            userEventTable.setUserAgent(userAgent);

            userEventTableRepository.save(userEventTable);

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
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

// ENVIO DE EMAIL
import com.springboot.spring.service.EmailService;
import com.springboot.spring.model.email.EmailConfirmBooking;
import com.springboot.spring.model.email.EmailDataConfirmBooking;
import java.text.SimpleDateFormat;

// FacturaScripts
import com.springboot.spring.service.FacturaScriptsService;

@CrossOrigin(origins = "http://bellidel.eu:8000")
@RestController
@RequestMapping("/booking")
public class BookingController {

    // LOGS
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // EMAIL
    @Autowired
    private EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserEventTableRepository userEventTableRepository;

    @Autowired
    BookingRepository bookingRepository;

    @PostMapping("")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, HttpServletRequest request) {
        try {
            
            logger.info("Booking: " + booking);

            if (booking == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                                        .getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername()).get();

            // COMPROBAMOS ESTADO DE MESA
            List<Booking> bookingList = bookingRepository.findBookingByBookingDayAndTurnIDAndTableID(booking.getBooking_day(), booking.getTurnID(), booking.getTableID());
            if (!bookingList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            // ALMACENAMOS LA RESERVA
            booking.setBookingID(0);
            booking.setStatus(0);

            Booking _booking = bookingRepository.save(new Booking(booking.getBookingID(), user.getClientID(),
                    booking.getTableID(), booking.getTurnID(), booking.getMenuID(), booking.getBooking_day(), booking.getDiners_number(),
                    booking.getStatus()));
            
            
            
            // ALMACENAMOS EL EVENTO DE RESERVA
            UserEventTable userEventTable = new UserEventTable();

            String ipAddress = request.getRemoteAddr(); 
            String userAgent = request.getHeader("User-Agent"); 

            userEventTable.setUserID(user.getClientID());
            userEventTable.setEventType("RESERVATION");
            userEventTable.setEventTimestamp(new Date());
            userEventTable.setUserIP(ipAddress);
            userEventTable.setEventDetails("Reservation");
            userEventTable.setUserAgent(userAgent);

            userEventTableRepository.save(userEventTable);

            // ENVIAMOS EL CORREO AL USUARIO
            EmailConfirmBooking email = new EmailConfirmBooking();

            email.setToken("asdadasdvs6eO1JYwXPvjIfu=cA9uKCJViUDwIzJmLffQWb!i-=DwBcywenAt?VR2CgRamVeIH=y5OJFO9E-I06!3?WFFj9S9AFQvX02gXsfOTI6jawIxcNVW!LqjDi5RfkJ8CRiYmR--??F3=1ZLzYeNPGHs/YArqJ-dInIrE4fv13o?bD0CYx54PK=?zn0C0-a?=wV9fUdmzJ2j8A/IOfjQj?aA44rBCp2H=GDkhKpnSUgqnUW51ITj19Wgb6f");
            email.setFrom("admin@bellidel.eu");
            email.setTo(user.getEmail());
            email.setSubject("Confirmación de reserva");
            email.setEmailType("createBooking");

            EmailDataConfirmBooking emailData = new EmailDataConfirmBooking();
            emailData.setName(user.getName());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(booking.getBooking_day());

            emailData.setDate(strDate);
            emailData.setTurn(booking.getTurnID().toString());
            emailData.setCapacity(booking.getDiners_number());

            email.setEmailData(emailData);
            logger.info("Email: " + email.toString());
            logger.info("EmailData: " + email.getToken());
            logger.info("EmailData: " + email.getFrom());
            logger.info("EmailData: " + email.getTo());
            logger.info("EmailData: " + email.getSubject());
            logger.info("EmailData: " + email.getEmailType());
            logger.info("EmailData: " + email.getEmailData().getName());
            logger.info("EmailData: " + email.getEmailData().getDate());
            logger.info("EmailData: " + email.getEmailData().getTurn());
            logger.info("EmailData: " + email.getEmailData().getCapacity());
            

            emailService.sendEmailConfirmBooking(email);

            // CREAMOS LA FACTURA EN FS
            User userFS = userRepository.findByUsername(userDetails.getUsername()).get();

            FacturaScriptsService facturaScriptsService = new FacturaScriptsService();
            Integer dinersNumber = booking.getDiners_number();
            facturaScriptsService.crearFactura("VZt9exqF0oy5czQXOnh7", userFS.getClientID().toString(), userFS.getClientID().toString(), userFS.getName(), userFS.getSurname(), booking.getBooking_day().toString(), booking.getTurnID().toString(), dinersNumber.toString(), booking.getMenuID().toString(), String.valueOf(_booking.getBookingID()));
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
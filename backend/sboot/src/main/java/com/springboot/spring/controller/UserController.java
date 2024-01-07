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
import com.springboot.spring.repository.BlacklistTokenRepository;
import com.springboot.spring.repository.UserEventTableRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.springboot.spring.security.jwt.AuthTokenFilter;
import com.springboot.spring.model.BlacklistToken;
import com.springboot.spring.model.UserEventTable;
import java.util.Date;
import com.springboot.spring.model.UnverifiedUser;
import com.springboot.spring.repository.UnverifiedUserRepository;

// ENVIO DE EMAIL
import com.springboot.spring.service.EmailService;
import com.springboot.spring.model.email.EmailCreateUser;
import com.springboot.spring.model.email.EmailDataCreateUser;
import java.text.SimpleDateFormat;
import com.springboot.spring.service.GenerateTemporalToken;

// BOOKINGS
import com.springboot.spring.model.Booking;
import com.springboot.spring.repository.BookingRepository;

// BookingsUser
import com.springboot.spring.model.BookingsUser;
import com.springboot.spring.repository.BookingUserRepository;

// FacturaScripts
import com.springboot.spring.service.FacturaScriptsService;
import com.springboot.spring.model.Factura;
import com.springboot.spring.model.ReservaConFactura;

@CrossOrigin(origins = "http://bellidel.eu:8000")
@RestController
@RequestMapping("/user")
public class UserController {

    // LOGS
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlacklistTokenRepository BlacklistTokenRepository;

    @Autowired
    private UserEventTableRepository userEventTableRepository;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UnverifiedUserRepository unverifiedUserRepository;

    @Autowired
    private GenerateTemporalToken generateTemporalToken;

    // EMAIL
    @Autowired
    private EmailService emailService;

    // BOOKINGS
    @Autowired
    private BookingRepository bookingRepository;

    // BOOKINGSUSER
    @Autowired
    private BookingUserRepository bookingUserRepository;

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

    // Get a profile of user by authorization bearer

    @GetMapping("/profile")
    public ResponseEntity<User> profile() {
    try {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (Exception e) {
        System.err.println(e);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}


    @PostMapping("/login")
    public ResponseEntity<UserWithToken> loginUser(@RequestBody User user, HttpServletRequest request) {
        try {
            
            if(unverifiedUserRepository.existsByUsername(user.getUsername()) != 0) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            if (userRepository.existsByUsername(user.getUsername()) == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));



            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String jwt = jwtUtils.generateJwtToken(authentication);

            User user_ = userRepository.findByUsername(user.getUsername()).get();

            UserWithToken userToken = new UserWithToken(jwt, user_);

            // SAVE LOGIN EVENT
            UserEventTable userEventTable = new UserEventTable();

             String ipAddress = request.getRemoteAddr(); // Obtains IP user
             String userAgent = request.getHeader("User-Agent"); // Obtains User Agent

            userEventTable.setUserID(user_.getClientID());
            userEventTable.setEventType("LOGIN");
            userEventTable.setEventTimestamp(new Date());
            userEventTable.setUserIP(ipAddress);
            userEventTable.setEventDetails("Login");
            userEventTable.setUserAgent(userAgent);

            userEventTableRepository.save(userEventTable);
            
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user, HttpServletRequest request) {
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

            // Almacenamos el registro de usuario
            UserEventTable userEventTable = new UserEventTable();

            userEventTable.setUserID(_user.getClientID());
            userEventTable.setEventType("REGISTER");
            userEventTable.setEventTimestamp(new Date());
            userEventTable.setUserIP(request.getRemoteAddr());
            userEventTable.setEventDetails("Register");
            userEventTable.setUserAgent(request.getHeader("User-Agent"));

            userEventTableRepository.save(userEventTable);

            // generamos un token aleatorio basado en el nombre del usuario y la fecha
            String _tmp_token = generateTemporalToken.generateToken(20);


            // almacenamos el usuario en unverifiedUser
            UnverifiedUser unverifiedUser = new UnverifiedUser();

            unverifiedUser.setClientID(_user.getClientID());
            unverifiedUser.setUsername(_user.getUsername());
            unverifiedUser.setTmp_token(_tmp_token);
            unverifiedUser.setAdmin_block(0);

            unverifiedUserRepository.save(unverifiedUser);

            // Enviamos el email de confirmación
            EmailCreateUser email = new EmailCreateUser();
            EmailDataCreateUser emailData = new EmailDataCreateUser();

            email.setToken("");
            email.setFrom("admin@bellidel.eu");
            email.setTo(_user.getEmail());
            email.setSubject("Confirmación de registro");
            email.setEmailType("createAccount");

            emailData.setName(_user.getName());
            emailData.setUsername(_user.getUsername());
            emailData.setToken(_tmp_token);

            email.setEmailData(emailData);

            emailService.sendEmailCreateUser(email);




            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        try {
            String token = authTokenFilter.parseJwt(request);
            if (BlacklistTokenRepository.TokenExist(token) == 0) {
                // Obten el token
                BlacklistToken blacklistToken = new BlacklistToken();

                // Obten información del usuario
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
                User user = userRepository.findByUsername(userDetails.getUsername()).get();

                // SAVE LOGIN EVENT
                UserEventTable userEventTable = new UserEventTable();

                String ipAddress = request.getRemoteAddr(); // Obtains IP user
                String userAgent = request.getHeader("User-Agent"); // Obtains User Agent

                userEventTable.setUserID(user.getClientID());
                userEventTable.setEventType("LOGOUT");
                userEventTable.setEventTimestamp(new Date());
                userEventTable.setUserIP(ipAddress);
                userEventTable.setEventDetails("Logout");
                userEventTable.setUserAgent(userAgent);

                userEventTableRepository.save(userEventTable);

                //Save token in blacklist
                blacklistToken.setClientID(user.getClientID());
                blacklistToken.setUsername(user.getUsername());
                blacklistToken.setToken(token);
                blacklistToken.setDate_logout(new Date());

                BlacklistTokenRepository.save(blacklistToken);
            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/confirmAccount")
    public ResponseEntity<?> confirmAccount(@RequestParam("token") String token) {
        try {

            if (unverifiedUserRepository.existsByTmpToken(token) == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            UnverifiedUser unverifiedUser = unverifiedUserRepository.findByTmpToken(token).get();

            // Damos de alta el usuario en FacturaScripts
            // buscamos el usuario por el username
            User user = userRepository.findByUsername(unverifiedUser.getUsername()).get();

            FacturaScriptsService facturaScriptsService = new FacturaScriptsService();


            facturaScriptsService.altaCliente("", user.getClientID().toString(), user.getClientID().toString(), user.getName(), user.getSurname() , user.getEmail(), user.getTlf().toString(), user.getUsername());

            unverifiedUserRepository.delete(unverifiedUser);

            return new ResponseEntity<>("Activation_ok",HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>("Activation_error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookings_history")
    public ResponseEntity<?> bookings_history(HttpServletRequest request){
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername()).get();

            List<BookingsUser> bookingsUser = bookingUserRepository.findBookingByClientID(user.getClientID());

            FacturaScriptsService facturaScriptsService = new FacturaScriptsService();
            List<Factura> facturas = facturaScriptsService.obtenerFacturas("",user.getClientID().toString());

            List<ReservaConFactura> combinedData = new ArrayList<>();

            for (BookingsUser booking : bookingsUser) {

                long bookingIdLong = booking.getBookingID();

                if (bookingIdLong <= Integer.MAX_VALUE && bookingIdLong >= Integer.MIN_VALUE) {
                    int bookingIdInt = (int) bookingIdLong;
                    
                    Factura matchingInvoice = facturas.stream()
                        .filter(f -> f.getId() == bookingIdInt)
                        .findFirst()
                        .orElse(null);

                    combinedData.add(new ReservaConFactura(booking, matchingInvoice));
                }
            }


            return new ResponseEntity<>(combinedData, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
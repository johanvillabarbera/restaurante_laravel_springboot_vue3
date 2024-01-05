package com.springboot.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
// import com.springboot.spring.model.Turns;
// import com.springboot.spring.model.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class BookingsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;

    @Column(name = "booking_day")
    private Date bookingDay;

    private String status;

    @Column(name = "diners_number")
    private int dinersNumber;

    @Column(name = "price_menu")
    private double priceMenu;

    @Column(name = "price_total")
    private double priceTotal;

    // @Column(name = "menuID")
    // private Long menu;

    @Column(name = "turn_hour")
    private String turn_hour;

    @Column(name = "name_menu")
    private String name_menu;

    // @Column(name = "clientID")
    // private Long client;
}

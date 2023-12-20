package com.springboot.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingID;

    @Column(name = "clientID")
    private Long clientID;

    @Column(name = "tableID")
    private Long tableID;

    @Column(name = "turnID")
    private Long turnID;

    @Column(name = "menuID")
    private Long menuID;

    @Column(name = "booking_day")
    private Date booking_day;

    @Column(name = "diners_number")
    private int diners_number;

    @Column(name = "status")
    private int status;

}
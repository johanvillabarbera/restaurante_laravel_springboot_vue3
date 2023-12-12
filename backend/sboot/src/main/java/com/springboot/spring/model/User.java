package com.springboot.spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "clients")

public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "clientID")
    private Long clientID;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "tlf")
    private String tlf;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date")
    private String birth_date;

    @Column(name = "IDrol")
    private String IDrol;   

    @Column(name = "username")
    private String username;
}

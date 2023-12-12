package com.springboot.spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "turns")

public class Turns {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "turnID")
    private Long turnID;

    @Column(name = "meal")
    private String meal;

    @Column(name = "turn_hour")
    private String turn_hour;
}
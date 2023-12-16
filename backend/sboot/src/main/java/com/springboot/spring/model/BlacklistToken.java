package com.springboot.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blacklist_token")
public class BlacklistToken {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "token")
    private String token;

}
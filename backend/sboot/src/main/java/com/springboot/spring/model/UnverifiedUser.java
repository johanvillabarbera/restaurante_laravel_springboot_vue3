package com.springboot.spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "unverified_users")

public class UnverifiedUser {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_unverified_user")
	private Long id_unverified_user;

    @Column(name = "clientID")
    private Long clientID;

    @Column(name = "username")
    private String username;

    @Column(name = "tmp_token")
    private String tmp_token;

    @Column(name = "admin_block")
    private int admin_block;

}
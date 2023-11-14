package com.springboot.spring.model;

import jakarta.persistence.*; 
import lombok.Data;

@Entity
@Data
@Table(name = "tables")
public class Tables {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tableID;
	private String location;
    private int capacity;
    private int availability;
}
package com.springboot.spring.model;

import jakarta.persistence.*; 
import lombok.Data;

@Entity
@Data
@Table(name = "tables")
public class Tables {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "tableID")
	private Long tableID;
	
	@Column(name = "capacity")
    private Integer capacity;

	@Column(name = "location")
	private String location;
	
	@Column(name = "availability")
    private Integer availability;
}
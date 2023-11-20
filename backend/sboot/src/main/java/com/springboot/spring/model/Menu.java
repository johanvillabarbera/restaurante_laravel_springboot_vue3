package com.springboot.spring.model;

import jakarta.persistence.*; 
import lombok.Data;

@Entity
@Data
@Table(name = "menus")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menuID")
	private Long menuID;
	
	@Column(name = "name")
    private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
    private Integer price;
}
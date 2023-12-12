package com.springboot.spring.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "ReservationFilters")
public class ReservationFiltersView {
    
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

    @Column(name = "turnID")
    private Integer turnID;

    @Column(name = "turn_hour")
    private String turn_hour;

    @Column(name = "booking_day")
    private Date booking_day;

    @Column(name = "estado_mesa")
    private Boolean estado_mesa;

    @Column(name = "estado_reserva")
    private Boolean estado_reserva;

    @Column(name = "meets_filters")
    private Boolean meets_filters;
}
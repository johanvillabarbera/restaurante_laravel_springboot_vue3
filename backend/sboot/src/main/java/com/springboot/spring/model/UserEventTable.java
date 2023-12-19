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
@Table(name = "user_event_table")
public class UserEventTable {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EventID")
    private long EventID;

    @Column(name = "UserID")
    private long UserID;

    @Column(name = "event_type")
    private String EventType;

    @Column(name = "event_timestamp")
    private Date EventTimestamp;

    @Column(name = "UserIP")
    private String UserIP;

    @Column(name = "event_details")
    private String EventDetails;

    @Column(name = "user_agent")
    private String UserAgent;

}
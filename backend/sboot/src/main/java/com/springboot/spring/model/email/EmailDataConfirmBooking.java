package com.springboot.spring.model.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDataConfirmBooking {
    private String name;
    private String date;
    private String turn;
    private int capacity;
}



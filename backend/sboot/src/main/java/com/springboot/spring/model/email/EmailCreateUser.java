package com.springboot.spring.model.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailCreateUser {
    private String token;
    private String from;
    private String to;
    private String subject;
    private String emailType;
    private EmailDataCreateUser emailData;
}
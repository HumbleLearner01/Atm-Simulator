package com.personalatm.service.mail;

import lombok.Data;

@Data
public class Email {
    private String subject;
    private String recipient;
    private String body;
}
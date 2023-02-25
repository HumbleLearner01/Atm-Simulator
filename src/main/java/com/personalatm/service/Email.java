package com.personalatm.service;

import lombok.Data;

@Data
public class Email {
    private String subject;
    private String recipient;
    private String body;
}
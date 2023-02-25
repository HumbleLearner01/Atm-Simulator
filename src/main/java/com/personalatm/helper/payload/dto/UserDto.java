package com.personalatm.helper.payload.dto;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Long userId;

    private String name;
    private String email;
    private String password;
    private boolean isEligible;
}
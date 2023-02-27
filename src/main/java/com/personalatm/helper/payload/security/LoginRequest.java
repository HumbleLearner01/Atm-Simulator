package com.personalatm.helper.payload.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    private @NotEmpty String accountNumber;
    private @NotEmpty String password;
}
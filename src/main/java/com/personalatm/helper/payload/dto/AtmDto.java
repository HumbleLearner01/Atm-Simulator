package com.personalatm.helper.payload.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class AtmDto implements Serializable {
    private Long atmId;

    @Size(min = 16,max = 16,message = "account number should be 16 digits!")
    @Pattern(regexp = "^[0-9]{16}$")
    private String accountNumber;
    private String password;
}
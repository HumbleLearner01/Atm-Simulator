package com.personalatm.helper.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class AccountManagementDto implements Serializable {
    private Long bankId;

    private String bankName;
    private String userAccountName;
    @Size(min = 16, max = 16, message = "cardNumber should be 16 characters")
    @Pattern(regexp = "^[0-9]{16}$")
    private String accountNumber;
    private String cvv2;
    private Date expirationDate;
    @CreationTimestamp
    private Date creationDate;
    private boolean activated;
    private Double balance;

    private UserDto user;
}

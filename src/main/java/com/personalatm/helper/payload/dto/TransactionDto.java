package com.personalatm.helper.payload.dto;

import com.personalatm.helper.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
public class TransactionDto implements Serializable {
    private long transactionId;

    private Date date;
    private BigDecimal amount;
    private String accountNumber;
    private TransactionType transactionType;
}
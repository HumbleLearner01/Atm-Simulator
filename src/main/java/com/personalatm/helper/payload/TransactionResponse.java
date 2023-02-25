package com.personalatm.helper.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private double requestedAmount;
    private double balanceRemaining;
    private String bankName;

    public TransactionResponse(double requestedAmount, double balanceRemaining) {
        this.requestedAmount = requestedAmount;
        this.balanceRemaining = balanceRemaining;
    }
}
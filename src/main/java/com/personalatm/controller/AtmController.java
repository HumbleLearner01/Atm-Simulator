package com.personalatm.controller;

import com.personalatm.helper.payload.TransactionResponse;
import com.personalatm.helper.payload.dto.AtmDto;
import com.personalatm.service.module.AtmService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
public class AtmController {
    private AtmService atmService;

    //withdraw money from user's account
    @GetMapping("/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@Valid @RequestBody AtmDto atmDto,
                                                        @RequestParam double amount,
                                                        @RequestParam String bankName) {
        TransactionResponse response = atmService.withdraw(atmDto, amount, bankName);
        return ResponseEntity.ok(new TransactionResponse(response.getRequestedAmount(), response.getBalanceRemaining(), bankName));
    }

    //TODO ::::: get the last 10 transactions
    //TODO ::::: transfer fund
}
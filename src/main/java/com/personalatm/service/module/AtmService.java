package com.personalatm.service.module;

import com.personalatm.helper.payload.TransactionResponse;
import com.personalatm.helper.payload.dto.AtmDto;
import org.springframework.stereotype.Service;

@Service
public interface AtmService {
    //withdraw money from the user's account
    TransactionResponse withdraw(AtmDto atmDto, double amount, String bankName);
    //transfer to one of the eligible users
    AtmDto transfer(AtmDto atmDto, String destinationAccountNumber);
    //deposit money to user's account
    AtmDto deposit(AtmDto atmDto, double amount);
    //TODO:last ten deposits
    //TODO:last ten withdraws
    //TODO:last ten transfers
}
package com.personalatm.service;

import com.personalatm.model.AccountManagement;
import com.personalatm.repository.AccountManagementRepository;
import com.personalatm.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
//TODO: implement account expiration scheduler
@Component
@AllArgsConstructor
public class Scheduler {
    private AccountManagementRepository accountRepo;
    private TransactionRepository transactionRepo;

    @Scheduled(cron = "0 0/15 * * * *") // run at midnight every day
    public void checkExpiredUsers() {
        Date currentDate = new Date();
        List<AccountManagement> expiredUsers = accountRepo.findByExpirationDateLessThan(currentDate);
        for (AccountManagement account : expiredUsers) {
            //TODO ::::: notify the user and disable or delete the account
        }
    }

    @Scheduled(cron = "0 0 0 * * *") // runs at midnight every day
    public void cleanupTransactions() {
        transactionRepo.deleteAll();
    }
}
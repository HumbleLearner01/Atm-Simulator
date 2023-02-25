package com.personalatm.service.module;

import com.personalatm.model.Transaction;
import com.personalatm.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    //get top 10 transactions made by user
    List<Transaction> getLast10Transactions(User user);
}
package com.personalatm.service.module.implementation;

import com.personalatm.model.Transaction;
import com.personalatm.model.User;
import com.personalatm.service.module.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getLast10Transactions(User user) {
        List<Transaction> transactions = user.getTransactions();
        if (transactions.size() < 10)
            return transactions;
        else return transactions.subList(transactions.size() - 10, transactions.size());
    }
}
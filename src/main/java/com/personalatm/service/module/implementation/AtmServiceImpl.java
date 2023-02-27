package com.personalatm.service.module.implementation;

import com.personalatm.helper.enums.TransactionType;
import com.personalatm.helper.exception.InsufficientBalanceException;
import com.personalatm.helper.exception.NoSuchAccountException;
import com.personalatm.helper.payload.TransactionResponse;
import com.personalatm.helper.payload.dto.AtmDto;
import com.personalatm.model.AccountManagement;
import com.personalatm.model.Transaction;
import com.personalatm.repository.AccountManagementRepository;
import com.personalatm.repository.TransactionRepository;
import com.personalatm.service.module.AtmService;
import com.personalatm.service.module.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class AtmServiceImpl implements AtmService {
    private AccountManagementRepository accountRepo;
    private TransactionRepository transactionRepo;
    private UserService userService;

    //withdraw an amount of money from the user's account after passing the credentials
    @Override
    public TransactionResponse withdraw(AtmDto atmDto, double amount, String bankName) {
        AccountManagement account = accountRepo.getUserByAccountNumberAndPassword(atmDto.getAccountNumber(), atmDto.getPassword(), bankName)
                .orElseThrow(() -> new NoSuchAccountException("There is no account with AccountNumber of: " + atmDto.getAccountNumber()));
        Double balance = userService.accountBalance(account.getAccountNumber(), account.getBankName());
        log.info("amount requested: {}", amount);
        log.info("balance before  : {}", balance);
        if (balance < amount)
            throw new InsufficientBalanceException("Insufficient balance. your balance is : " + balance);
        balance -= amount;
        log.info("balance after   : {}", balance);
        account.setBalance(balance);
        accountRepo.save(account);

        Transaction transaction = Transaction.builder()
                .accountNumber(account.getAccountNumber())
                .amount(BigDecimal.valueOf(amount))
                .transactionType(TransactionType.WITHDRAW)
                .description("" + TransactionType.WITHDRAW)
                .build();
        transactionRepo.save(transaction);

        return new TransactionResponse(amount, balance);
    }

    @Override
    public AtmDto transfer(AtmDto atmDto, String destinationAccountNumber) {
        return null;
    }

    @Override
    public AtmDto deposit(AtmDto atmDto, double amount) {
        return null;
    }
}
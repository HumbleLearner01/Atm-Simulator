package com.personalatm.service.module.implementation;

import com.personalatm.helper.exception.ResourceNotFoundException;
import com.personalatm.helper.exception.UserNotEligibleException;
import com.personalatm.helper.payload.dto.AccountManagementDto;
import com.personalatm.model.AccountManagement;
import com.personalatm.model.User;
import com.personalatm.repository.AccountManagementRepository;
import com.personalatm.repository.UserRepository;
import com.personalatm.service.module.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountManagementRepository accountRepo;
    private UserRepository userRepo;
    private ModelMapper mapper;

    //create a new account for eligible user only
    @Override
    @Transactional
    public void createAccount(AccountManagementDto accountManagementDto) {
        User user = userRepo.findById(accountManagementDto.getUser().getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserID", accountManagementDto.getUser().getUserId().toString()));
        if (user.isEligible()) {
            Date date = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10));

            AccountManagement account = mapper.map(accountManagementDto, AccountManagement.class);
            account.setExpirationDate(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)));
            account.setUser(user);
            account.setUserAccountName(user.getName());
            account.setAccountNumber(accountManagementDto.getAccountNumber());
            if (accountManagementDto.getBalance() != null)
                account.setBalance(accountManagementDto.getBalance());
            else
                account.setBalance(0.0);
            log.info("accountDTO: {}", accountManagementDto);
            log.info("account   : {}", account);
            log.info("expiration date: {}", date);

            mapper.map(accountRepo.save(account), AccountManagementDto.class);
        } else
            throw new UserNotEligibleException("provided user, does not meet our requirements! pls proceed to activating the user first.");
    }

    //find the account by user id
    @Override
    @Transactional(readOnly = true)
    public List<AccountManagementDto> findAccountByUserId(long userId) {
        List<AccountManagement> accounts = accountRepo.findAccountManagementsByUserUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "UserID", String.valueOf(userId)));
        return accounts.stream().map(account ->
                        mapper.map(account, AccountManagementDto.class))
                .collect(Collectors.toList());
    }

    //find all accounts in the specific bank
    @Override
    @Transactional(readOnly = true)
    public List<AccountManagementDto> findAllAccountsOfBank(String bankName) {
        return accountRepo.findByBankName(bankName)
                .stream().map(accounts ->
                        mapper.map(accounts, AccountManagementDto.class))
                .collect(Collectors.toList());
    }

    //TODO: implement the delete correctly. an account can be deleted only if the user has requested a permission from admin
    //delete account
    @Override
    @Transactional
    public void deleteAccountByUserId(long userId) {
        AccountManagement account = accountRepo.findByUserUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "UserID", String.valueOf(userId)));
        accountRepo.delete(account);
    }

    //deposit money into the user's account
    @Override
    @Transactional
    public AccountManagementDto depositMoney(AccountManagementDto accountDto) {
        AccountManagement account = accountRepo.findByAccountNumberAndBankNameLike(accountDto.getAccountNumber(), accountDto.getBankName())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber & BankName", accountDto.getAccountNumber() + ", " + accountDto.getBankName()));
        account.setBalance(accountDto.getBalance() + account.getBalance());
        accountRepo.save(account);
        return mapper.map(account, AccountManagementDto.class);
    }
}
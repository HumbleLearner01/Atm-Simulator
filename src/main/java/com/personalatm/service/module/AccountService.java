package com.personalatm.service.module;

import com.personalatm.helper.payload.dto.AccountManagementDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    //create account
    void createAccount(AccountManagementDto accountManagementDto);

    //get single account
    List<AccountManagementDto> findAccountByUserId(long userId);

    //get all account
    List<AccountManagementDto> findAllAccountsOfBank(String bankName);

    //delete a account by id
    void deleteAccountByUserId(long userId);

    //deposit money into the user's account
    AccountManagementDto depositMoney(AccountManagementDto accountManagementDto);
}
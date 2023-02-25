package com.personalatm.controller;

import com.personalatm.helper.payload.ApiResponse;
import com.personalatm.helper.payload.dto.AccountManagementDto;
import com.personalatm.service.module.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account-manager")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;
    //TODO ::::: If the user is expired, no actions should be allowed

    //create a new account for user
    @PostMapping
    public ResponseEntity<ApiResponse> createAccount(@Valid @RequestBody AccountManagementDto accountManagementDto) {
        accountService.createAccount(accountManagementDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Account created successfully!", true));
    }

    //deposit money into the user's account
    @PutMapping
    public ResponseEntity<?> depositMoneyIntoAccount(@RequestBody AccountManagementDto accountDto) {
        return ResponseEntity.ok(accountService.depositMoney(accountDto));
    }

    //get all accounts of a specific bank
    @GetMapping("/{bankName}")
    public ResponseEntity<List<AccountManagementDto>> getAllAccountsOfBank(@PathVariable String bankName) {
        return ResponseEntity.ok(accountService.findAllAccountsOfBank(bankName));
    }

    //get all accounts of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountManagementDto>> getAllAccountsOfUser(@PathVariable long userId) {
        return ResponseEntity.ok(accountService.findAccountByUserId(userId));
    }

    //TODO: delete account by user id
}
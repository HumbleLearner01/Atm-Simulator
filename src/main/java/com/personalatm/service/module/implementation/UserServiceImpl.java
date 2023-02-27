package com.personalatm.service.module.implementation;

import com.personalatm.helper.exception.ResourceNotFoundException;
import com.personalatm.helper.payload.dto.UserDto;
import com.personalatm.model.AccountManagement;
import com.personalatm.model.User;
import com.personalatm.repository.AccountManagementRepository;
import com.personalatm.repository.UserRepository;
import com.personalatm.service.module.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;
    private AccountManagementRepository bankRepo;
    private ModelMapper mapper;

    //create a new user
    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User user = userRepo.save(mapper.map(userDto, User.class));
        return mapper.map(user, UserDto.class);
    }

    //find a single user by id
    @Override
    @Transactional(readOnly = true)
    public UserDto findById(long userId) {
        User user = userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "UserID", String.valueOf(userId)));
        return mapper.map(user, UserDto.class);
    }

    //find all the users
    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepo.findAll()
                .stream().map(user ->
                        mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    //delete a user by id
    @Override
    @Transactional
    public void deleteById(long userId) {
        UserDto userDto = findById(userId);
        userRepo.delete(mapper.map(userDto, User.class));
    }

    //update a user
    @Override
    @Transactional
    public UserDto saveUpdate(UserDto userDto) {
        User user = userRepo.findById(userDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserID", String.valueOf(userDto.getUserId())));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepo.save(mapper.map(userDto, User.class));
        return mapper.map(updatedUser, UserDto.class);
    }

    //get the balance of a user from the BankAccount
    @Override
    @Transactional(readOnly = true)
    public Double accountBalance(long userId, String bankName) {
        Double accountBalance = bankRepo.getAccountBalanceByUserAndBankName(userId, bankName);
        log.info("findByAccountBalanceGreaterThanEqual: {}", accountBalance);
        return accountBalance != null ? accountBalance : 0;
    }

    //get the balance of a user by account number and bank name
    @Override
    @Transactional(readOnly = true)
    public Double accountBalance(String accountNumber, String bankName) {
        AccountManagement account = bankRepo.findByAccountNumberAndBankNameLike(accountNumber, bankName)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber & BankName", accountNumber + ", " + bankName));
        return account.getBalance();
    }
}
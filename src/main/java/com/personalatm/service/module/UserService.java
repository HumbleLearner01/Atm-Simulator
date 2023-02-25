package com.personalatm.service.module;

import com.personalatm.helper.payload.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //create user
    UserDto save(UserDto userDto);

    //get single user
    UserDto findById(long userId);

    //get all users
    List<UserDto> findAll();

    //delete a user by id
    void deleteById(long userId);

    //update user by id
    UserDto saveUpdate(UserDto userDto);

    //get the balance value of user
    Double accountBalance(long userId, String bankName);

    //get the balance of a user by account number and bank name
    Double accountBalance(String accountNumber, String bankName);
}
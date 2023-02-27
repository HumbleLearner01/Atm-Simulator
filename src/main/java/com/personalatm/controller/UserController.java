package com.personalatm.controller;

import com.personalatm.helper.payload.ApiResponse;
import com.personalatm.helper.payload.dto.UserDto;
import com.personalatm.service.module.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    //TODO ::::: add the confirm email implementation, to request for making the user eligible
    //create user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    //find user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    //find all users
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    //delete user by id
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable long userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully!", true), HttpStatus.OK);
    }

    //update user
    @PutMapping
    public ResponseEntity<UserDto> saveUpdate(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUpdate(userDto));
    }

    //get the account balance value of the user
    @GetMapping("/account/balance/{userId}")
    public ResponseEntity<?> accountBalance(@PathVariable long userId,
                                            @RequestParam String bankName) {
        return new ResponseEntity<>(userService.accountBalance(userId,bankName),HttpStatus.OK);
    }
}
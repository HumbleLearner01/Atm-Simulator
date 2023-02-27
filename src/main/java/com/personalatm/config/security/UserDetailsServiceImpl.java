package com.personalatm.config.security;


import com.personalatm.helper.exception.ResourceNotFoundException;
import com.personalatm.model.AccountManagement;
import com.personalatm.model.User;
import com.personalatm.repository.AccountManagementRepository;
import com.personalatm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountManagementRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        AccountManagement account = userRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("UserAccount", "AccountNumber", accountNumber));
        User user = account.getUser();
        return new org.springframework.security.core.userdetails.User(
                account.getAccountNumber(), user.getPassword(), user.isEligible(),
                true, true, true,
                grantedAuthorities("USER"));
    }

    //TODO: should implement multiple roles and role based authentication
    private Collection<? extends GrantedAuthority> grantedAuthorities(String role) {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
}
package com.personalatm.helper.exception;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NoSuchAccountException extends RuntimeException {
    public NoSuchAccountException(@Size(min = 16, max = 16, message = "account number should be 16 digits!") @Pattern(regexp = "^[0-9]{16}$") String s) {
        super(s);
    }
}
package com.testetgid.testetgid.exeptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message){
        super(message);
    }
}

package com.testetgid.testetgid.model;

public enum TransactionType {
    DEPOSIT("deposit"), WITHDRAW("withdraw");

    private String description;

    TransactionType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}

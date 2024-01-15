package com.testetgid.testetgid.dto;

import java.math.BigDecimal;

import com.testetgid.testetgid.model.TransactionType;

public record TransactionDTO(String cpf, String cnpj, TransactionType type, BigDecimal amount) {
    
}

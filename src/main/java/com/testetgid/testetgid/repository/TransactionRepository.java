package com.testetgid.testetgid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testetgid.testetgid.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}

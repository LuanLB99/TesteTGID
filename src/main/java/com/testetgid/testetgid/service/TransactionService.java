package com.testetgid.testetgid.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testetgid.testetgid.dto.TransactionDTO;
import com.testetgid.testetgid.exeptions.InsufficientFundsException;
import com.testetgid.testetgid.exeptions.NotFoundException;
import com.testetgid.testetgid.model.Company;
import com.testetgid.testetgid.model.Transaction;
import com.testetgid.testetgid.model.TransactionType;
import com.testetgid.testetgid.model.User;
import com.testetgid.testetgid.repository.CompanyRepository;
import com.testetgid.testetgid.repository.TransactionRepository;
import com.testetgid.testetgid.repository.UserRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public TransactionService(TransactionRepository transactionRepository, 
                              UserRepository userRepository, 
                              CompanyRepository companyRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
       
    }

        @Transactional
    public void createTransaction(TransactionDTO transactionDTO) {

        Optional<User> userOptional = userRepository.findByCpf(transactionDTO.cpf());
        Optional<Company> companyOptional = companyRepository.findByCnpj(transactionDTO.cnpj());

        if(userOptional.isPresent() && companyOptional.isPresent()){
            User user = userOptional.get();
            Company company = companyOptional.get();

            Transaction transaction = new Transaction(user, company, transactionDTO.type(), transactionDTO.amount());

            BigDecimal amountWithTax = calculateAmountWithTax(transactionDTO.amount(), company.getTransactionTax());
        
            updateBalances(user, company, amountWithTax, transactionDTO.type());

            transactionRepository.save(transaction);

        } else {
            throw new NotFoundException("Usuário ou empresa não encontrados!");
        }

    }

    private BigDecimal calculateAmountWithTax(BigDecimal amount, BigDecimal tax) {
        return amount.subtract(tax);
    }

    private void updateBalances(User user, Company company, BigDecimal amountWithTax, TransactionType type) {

        if (TransactionType.DEPOSIT.equals(type)) {
           
            if (user.getBalance().compareTo(amountWithTax) >= 0) {
            user.setBalance(user.getBalance().subtract(amountWithTax));
            company.setBalance(company.getBalance().add(amountWithTax));
            } else {
                throw new InsufficientFundsException("Saldo insuficiente para realizar o depósito.");
            }

        } else if (TransactionType.WITHDRAW.equals(type)) {
           
            if (company.getBalance().compareTo(amountWithTax) >= 0) {
            user.setBalance(user.getBalance().add(amountWithTax));
            company.setBalance(company.getBalance().subtract(amountWithTax));
        }   else {
            throw new InsufficientFundsException("Fundos insuficientes para realizar a retirada.");
        }
        }
        userRepository.save(user);
        companyRepository.save(company);
    }
    
    public List<Transaction>listTransactions(){
        return transactionRepository.findAll();
    }

    
}


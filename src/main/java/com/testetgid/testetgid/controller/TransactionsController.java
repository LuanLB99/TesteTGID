package com.testetgid.testetgid.controller;

import org.springframework.web.bind.annotation.RestController;

import com.testetgid.testetgid.dto.TransactionDTO;
import com.testetgid.testetgid.exeptions.CNPJInvalidException;
import com.testetgid.testetgid.exeptions.CPFInvalidException;
import com.testetgid.testetgid.exeptions.InsufficientFundsException;
import com.testetgid.testetgid.exeptions.NotFoundException;
import com.testetgid.testetgid.model.Transaction;
import com.testetgid.testetgid.service.TransactionService;
import com.testetgid.testetgid.validator.CnpjValidator;
import com.testetgid.testetgid.validator.CpfValidator;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/transactions")

public class TransactionsController {

    @Autowired
    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> listTransactions() {
        return transactionService.listTransactions();
    }
    

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        
        try {
            CnpjValidator.validateCNPJ(transactionDTO.cnpj());
            CpfValidator.validateCPF(transactionDTO.cpf());
            transactionService.createTransaction(transactionDTO);
            return ResponseEntity.ok("Transação criada com sucesso!");
        }
         catch (CPFInvalidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: CPF inválido!");
         }  
        catch (CNPJInvalidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: CNPJ inválido!");
         }
         catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Fundos Insuficientes");
         }  
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Usuário ou Empresa não encontrado");
         }  
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
        
    }
    
}

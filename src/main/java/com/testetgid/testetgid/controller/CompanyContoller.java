package com.testetgid.testetgid.controller;

import org.springframework.web.bind.annotation.RestController;

import com.testetgid.testetgid.dto.CompanyDTO;
import com.testetgid.testetgid.exeptions.CNPJInvalidException;
import com.testetgid.testetgid.model.Company;
import com.testetgid.testetgid.service.CompanyService;
import com.testetgid.testetgid.validator.CnpjValidator;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/company")

public class CompanyContoller {

    @Autowired
    private CompanyService companyService;
    
    @GetMapping
    public List<Company> listCompanies() {
        return companyService.listCompanies();
    }

    @PostMapping
    public ResponseEntity<String> createCompanies(@RequestBody @Valid CompanyDTO company) {
       //To do: Tratar erro do email.
        try {
            CnpjValidator.validateCNPJ(company.cnpj());
            companyService.createCompany(company);
            return ResponseEntity.ok("Empresa cadastrada com sucesso!");
        } catch (CNPJInvalidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: CNPJ inválido!");
         } 
        catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }
    
}

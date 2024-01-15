package com.testetgid.testetgid.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testetgid.testetgid.model.Company;


public interface CompanyRepository extends JpaRepository <Company, Long> {
     Optional<Company> findByCnpj(String cnpj);   
} 

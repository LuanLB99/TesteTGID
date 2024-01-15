package com.testetgid.testetgid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testetgid.testetgid.dto.CompanyDTO;
import com.testetgid.testetgid.model.Company;
import com.testetgid.testetgid.repository.CompanyRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> listCompanies(){
        return companyRepository.findAll();
    }

    public void createCompany(CompanyDTO company){
        companyRepository.save(new Company(company));
    }
}

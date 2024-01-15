package com.testetgid.testetgid.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.testetgid.testetgid.dto.CompanyDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "companies")
@NoArgsConstructor
public class Company {

    public Company(CompanyDTO data){
        this.name = data.name();
        this.cnpj = data.cnpj();
        this.email = data.email();
        this.balance = BigDecimal.valueOf(4000,00);
        this.transactionTax = sortTaxTransaction();
    }

    @JsonBackReference
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @Column(length = 14, nullable = false, unique = true)
    @Size(min=14, max=14)
    @NotBlank(message = "O CPF não pode estar em branco")
    private String cnpj;

    @Column(length = 100, nullable = false, unique = true)
    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private BigDecimal transactionTax;


     private BigDecimal sortTaxTransaction() {

        double valorAleatorio = (Math.random() * (1.50)) + 0.50;

        return BigDecimal.valueOf(valorAleatorio).setScale(2, RoundingMode.HALF_UP);
    }
}

package com.testetgid.testetgid.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.testetgid.testetgid.dto.UserDTO;

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
@Table(name = "Users")
@NoArgsConstructor

public class User {
    
    public User(UserDTO data){
        this.name = data.name();
        this.cpf = data.cpf();
        this.email = data.email();
        this.balance = BigDecimal.ZERO;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @Column(length = 11, nullable = false, unique = true)
    @Size(min=11, max=11)
    @NotBlank(message = "O CPF não pode estar em branco")
    private String cpf;

    @Column(length = 100, nullable = false, unique = true)
    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @Column(nullable = false)
    private BigDecimal balance;

}

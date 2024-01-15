package com.testetgid.testetgid.controller;

import org.springframework.web.bind.annotation.RestController;

import com.testetgid.testetgid.dto.UserDTO;
import com.testetgid.testetgid.exeptions.CPFInvalidException;
import com.testetgid.testetgid.model.User;
import com.testetgid.testetgid.service.UserService;
import com.testetgid.testetgid.validator.CpfValidator;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/api/users")
public class UsersController {


    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listUsers() {
        return userService.listUsers();
    }
    
    
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDTO user) {
        //To do: Tratar erro do email.
        try {
            CpfValidator.validateCPF(user.cpf());
            userService.createUser(user);
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        }
        catch (CPFInvalidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: CPF inválido!");
         } 
         catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }
}

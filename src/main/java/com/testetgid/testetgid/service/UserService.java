package com.testetgid.testetgid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testetgid.testetgid.dto.UserDTO;
import com.testetgid.testetgid.model.User;
import com.testetgid.testetgid.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }
    
    public void createUser(UserDTO user){
        userRepository.save(new User(user));
    }
}

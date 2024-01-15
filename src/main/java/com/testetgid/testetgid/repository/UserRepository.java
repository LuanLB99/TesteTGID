package com.testetgid.testetgid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testetgid.testetgid.model.User;

public interface UserRepository extends JpaRepository <User, Long>{
    
}

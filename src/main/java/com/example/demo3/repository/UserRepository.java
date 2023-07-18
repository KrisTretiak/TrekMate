package com.example.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo3.entity.User;


//declares a custom method in JpaRepository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}

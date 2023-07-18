package com.example.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo3.entity.Role;

//declares a custom method in JpaRepository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
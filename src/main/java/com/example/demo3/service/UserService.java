package com.example.demo3.service;

import java.util.List;

import com.example.demo3.dto.UserDto;
import com.example.demo3.entity.User;



public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
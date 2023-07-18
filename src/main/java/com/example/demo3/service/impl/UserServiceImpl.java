package com.example.demo3.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo3.dto.UserDto;
import com.example.demo3.entity.Role;
import com.example.demo3.entity.User;
import com.example.demo3.repository.RoleRepository;
import com.example.demo3.repository.UserRepository;
import com.example.demo3.service.UserService;

//defines several methods for extracting/adding data into the database 
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

//This method collects info from the "register" form and saves it into the database 
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setAge(userDto.getAge());
        user.setLocation(userDto.getLocation());
        user.setDifficulty(userDto.getDifficulty());

 // grand admin rights to users with email = "admin@gmail.com" only
        if (user.getEmail().equals("admin@gmail.com")) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = checkRoleExistAdmin();
            }
            user.setRoles(Arrays.asList(adminRole));
        } else {
            Role userRole = roleRepository.findByName("ROLE_USER");
            if (userRole == null) {
                userRole = checkRoleExist();
            }
            user.setRoles(Arrays.asList(userRole));
        }
        userRepository.save(user);
    } 

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
         return roleRepository.save(role);
    }

    private Role checkRoleExistAdmin() {
          Role role = new Role();
          role.setName("ROLE_ADMIN");
           return roleRepository.save(role);
    }

    //This method is needed for log in. 
//It finds a user in the database by their email address(returns an User entity)
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//This method retrieves a list of all users from the database
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setAge(user.getAge());
        userDto.setLocation(user.getLocation());
        userDto.setDifficulty(user.getEmail());
        return userDto;
    }


}

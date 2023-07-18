package com.example.demo3.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo3.dto.UserDto;
import com.example.demo3.entity.User;
import com.example.demo3.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
//landing page
//"log out" also directs here. see html for more 
    @GetMapping("/LandingPage")
    public String home(){
        return "LandingPage";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }


    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        //where to go after registered? 
        return "userPage";
    }


    @PostMapping("/userPage/edit")
    public String gottoedit( Model model){
        // int id = Integer.parseInt(uid);
        // User u = userRepo.findById(id).get();
        // model.addAttribute("us", u);
        return "edit.html";
    } 


    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}

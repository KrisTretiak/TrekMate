package com.example.demo3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//class to transfer the data between the controller layer and the view layer
public class UserDto
{
    private Integer id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    
    @NotEmpty(message = "Password should not be empty")
    private String password;

    private Integer age;

    @NotEmpty //(message = "Location should not be empty")
    private String location;

    @NotEmpty //(message = "Difficulty should not be empty")
    private String difficulty;
}

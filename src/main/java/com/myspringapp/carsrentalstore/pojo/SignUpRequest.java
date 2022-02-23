package com.myspringapp.carsrentalstore.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Set<String> roles;
}

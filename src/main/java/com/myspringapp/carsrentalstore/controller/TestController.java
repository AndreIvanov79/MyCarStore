package com.myspringapp.carsrentalstore.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @GetMapping("/all")
    public String getAll(){
        return "Public API";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN','OPERATOR')")
    public String getUserApi(){
        return "User API";
    }

    @GetMapping("/oper")
    //@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
    public String getOperApi(){
        return "Operator API";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminApi(){
        return "Admin API";
    }
}

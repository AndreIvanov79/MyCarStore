package com.myspringapp.carsrentalstore.controller;

import com.myspringapp.carsrentalstore.service.RentServiceImpl;
import io.swagger.annotations.Api;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RentController {
    @Autowired
    private RentServiceImpl rentService;

    @GetMapping("/rents")
    public ResponseEntity<?> getAllRents(){
        try {
            return new ResponseEntity<>(rentService.getAllRents(), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Server doesn't respond. Database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/rents/id/{id}")
    public ResponseEntity<?> getRentById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(rentService.getRentById(id), HttpStatus.OK);
        } catch (HibernateException ex) {
            return new ResponseEntity<>("Rent by ID " + id + "  not found in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

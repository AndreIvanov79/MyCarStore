package com.myspringapp.carsrentalstore.controller;

import com.myspringapp.carsrentalstore.service.CarServiceImpl;
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
public class CarController {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/cars")
    public ResponseEntity<?> getAllCars(){
        try {
            return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Server doesn't respond. Database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/cars/id/{id}")
    public ResponseEntity<?> getCarById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
        } catch (HibernateException ex) {
            return new ResponseEntity<>("Car by ID " + id + "  not found in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/ofuser/id/{id}")
    public ResponseEntity<?> getUsersCars(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(carService.getUsersCars(id), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Server doesn't respond. Database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/rented")
    public ResponseEntity<?> getListRentedCars(){
        try {
            return new ResponseEntity<>(carService.getListRentedCars(), HttpStatus.OK);
        } catch (HibernateException ex){
            return new ResponseEntity<>("All cars are free", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cars/free")
    public ResponseEntity<?> getListFreeCars(){
        try {
            return new ResponseEntity<>(carService.getListFreeCars(), HttpStatus.OK);
        } catch (HibernateException ex){
            return new ResponseEntity<>("All cars are rented", HttpStatus.NOT_FOUND);
        }
    }
}

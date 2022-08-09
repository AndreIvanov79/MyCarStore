package com.myspringapp.carsrentalstore.controller;

import com.myspringapp.carsrentalstore.service.VehicleServiceImpl;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
    @Autowired
    private VehicleServiceImpl vehicleService;

    @GetMapping("/vehicles")
    public ResponseEntity<?> getAllVehicles(){
        try {
            return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Server doesn't respond. Database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/vehicles/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
        } catch (HibernateException ex) {
            return new ResponseEntity<>("User by ID " + id + "  not found in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/vehicles/cartype/id/{id}")
    public ResponseEntity<?> getTypeOfCar(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(vehicleService.getTypeOfCar(id), HttpStatus.OK);
        } catch (HibernateException ex) {
            return new ResponseEntity<>("Vehicle Type with ID " + id + "  not found in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

package com.myspringapp.carsrentalstore.controller;

import com.myspringapp.carsrentalstore.dto.DtoMapper;
import com.myspringapp.carsrentalstore.service.RentServiceImpl;
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
public class RentController {
    @Autowired
    private RentServiceImpl rentService;

    @Autowired
    private DtoMapper dtoMapper;

    @GetMapping("/rents")
    public ResponseEntity<?> getAllRents(){
        try {
            return new ResponseEntity<>(rentService.getAllRents().stream().map(rent -> dtoMapper.toRentDto(rent)), HttpStatus.OK);
//            return new ResponseEntity<>(rentService.getAllRents(), HttpStatus.OK);
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

    @GetMapping(value = "/rents/ofuser/id/{id}")
    public ResponseEntity<?> getRentsOfUser(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(rentService.getRentsOfUser(id), HttpStatus.OK);
        } catch (HibernateException ex) {
            return new ResponseEntity<>("User with id["+id+"] has no rents.", HttpStatus.NOT_FOUND );
        }
    }

    @GetMapping("/rents/finished")
    public ResponseEntity<?> getAllFinishedRents(){
        try {
            return new ResponseEntity<>(rentService.getAllFinishedRents(), HttpStatus.OK);
        } catch (HibernateException e){
            return new ResponseEntity<>("There are no finished rents in that moment", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/rents/current")
    public ResponseEntity<?> getAllCurrentRents(){
        try {
            return new ResponseEntity<>(rentService.getAllCurrentRents(), HttpStatus.OK);
        } catch (HibernateException e){
            return new ResponseEntity<>("There are no current rents in that moment", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/rents/lists")
    public ResponseEntity<?> getRentListsOfCurrentAndFinished(){
        try {
            return new ResponseEntity<>(rentService.getListOfRentsByFinishedFlag(rentService.getAllRents().stream()), HttpStatus.OK);
        } catch (HibernateException e){
            return new ResponseEntity<>("There are no current rents in that moment", HttpStatus.NOT_FOUND);
        }
    }
}

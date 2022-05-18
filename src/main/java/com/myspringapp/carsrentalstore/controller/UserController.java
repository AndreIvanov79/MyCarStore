package com.myspringapp.carsrentalstore.controller;

import com.myspringapp.carsrentalstore.model.Rent;
import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.service.CarServiceImpl;
import com.myspringapp.carsrentalstore.service.RentServiceImpl;
import com.myspringapp.carsrentalstore.service.UserServiceImpl;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RentServiceImpl rentService;

    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Server doesn't respond. Database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/users/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (HibernateException ex) {
            return new ResponseEntity<>("User by ID " + id + "  not found in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> saveOrUpdateUser(@RequestBody User user) {
        try {
            User newUser=userService.saveOrUpdateUser(user);
            return new ResponseEntity<User>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Cannot create new user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/users/delete/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<String>("User was deleted successfully", HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<String>("User (id: " + id + ")  not found", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (HibernateException e) {
            return new ResponseEntity<String>("User can't be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/id/{id}/rents")
    public ResponseEntity<?> getAllUsersRents(@PathVariable("id") Long id){
        List<Rent> userRents=rentService.getRentsOfUser(id).stream().collect(Collectors.toList());
        try {
            return new ResponseEntity<List<Rent>>(userRents, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Server doesn't respond. Database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/id/{id}/role")
    public ResponseEntity<?> getUsersRole(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(userService.getUserRole(id), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Server doesn't respond. Database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/id/{id}/rrole")
    public ResponseEntity<?> setOfUsersRole(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(userService.setOfUserRoles(id), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Server doesn't respond. Database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.Rent;
import com.myspringapp.carsrentalstore.model.Role;
import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUserName(username);
    }

    public User saveOrUpdateUser(User user){
        userRepository.saveAndFlush(user);
        return user;
    }

    public void deleteUser(long id){
        userRepository.delete(userRepository.getById(id));
    }

    public Optional<Rent> getRentsOfUser(long userId){
        return userRepository.getUsersRents(userId);
    }

    public Set<Role> getUserRole(long userId){
        User user=userRepository.getById(userId);
        return user.getRoles();
    }

    public Car getUsersCars(long id){
        Optional<Rent> rents=userRepository.getUsersRents(id);
        Car car=rents.get().getCarId();
        return car;
    }
}

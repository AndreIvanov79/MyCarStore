package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.Rent;
import com.myspringapp.carsrentalstore.model.Role;
import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.repository.RoleRepository;
import com.myspringapp.carsrentalstore.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

@Log4j2
@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentServiceImpl rentService;

    private RoleRepository roleRepository;

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

    public boolean deleteUser(long id){
        Boolean isDeleted = true;
        userRepository.delete(userRepository.getById(id));
        if (userRepository.existsByUserName(userRepository.getById(id).getUserName())){
            isDeleted=false;
        }
        return isDeleted;
    }



    public Set<Role> getUserRole(long userId){
        User user=userRepository.getById(userId);
        return user.getRoles();
    }

    public Set<Role> setOfUserRoles(long id){
        Set<Role> roles=roleRepository.getUsersRole(id).stream().collect(Collectors.toSet());
        return roles;
    }

    public List<User> getUsersWithCurrentRents(){
       return rentService.getAllCurrentRents().stream().map(rent -> rent.getUserId()).collect(Collectors.toList());
    }

}

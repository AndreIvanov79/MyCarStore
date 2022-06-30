package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Role;
import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.repository.RoleRepository;
import com.myspringapp.carsrentalstore.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentServiceImpl rentService;

    private RoleRepository roleRepository;

    public List<User> getAllUsers(){
        log.info("Started getAllUsers method.");
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id){
        log.info("Started getUserByID method."); return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username){
        log.info("Started getUserByUsername method.");
        return userRepository.findByUserName(username);
    }

    public User saveOrUpdateUser(User user){
        log.info("Started saveOrUpdateUser method.");
        userRepository.saveAndFlush(user);
        return user;
    }

    public boolean deleteUser(long id){
        log.info("Started deleteUser method.");
        Boolean isDeleted = true;
        userRepository.delete(userRepository.getById(id));
        if (userRepository.existsByUserName(userRepository.getById(id).getUserName())){
            isDeleted=false;
        }
        return isDeleted;
    }



    public Set<Role> getUserRole(long userId){
        log.info("Started getUserRole method.");
        User user=userRepository.getById(userId);
        return user.getRoles();
    }

    public Set<Role> setOfUserRoles(long id){
        log.info("Started setOfUserRole method.");
        Set<Role> roles=roleRepository.getUsersRole(id).stream().collect(Collectors.toSet());
        return roles;
    }

    public List<User> getUsersWithCurrentRents(){
        log.info("Started getUsersWithCurrentRents method.");
       return rentService.getAllCurrentRents().stream().map(rent -> rent.getUserId()).collect(Collectors.toList());
    }

}

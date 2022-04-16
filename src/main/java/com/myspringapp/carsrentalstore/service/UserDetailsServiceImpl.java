package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserServiceImpl userService;

    @Autowired
    public UserDetailsServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User "+username+" not found"));
        return UserDetailsImpl.build(user);
    }
}

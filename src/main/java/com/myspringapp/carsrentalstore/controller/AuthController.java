package com.myspringapp.carsrentalstore.controller;

import com.myspringapp.carsrentalstore.config.jwt.JwtUtils;
import com.myspringapp.carsrentalstore.model.ERole;
import com.myspringapp.carsrentalstore.model.Role;
import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.pojo.JwtResponse;
import com.myspringapp.carsrentalstore.pojo.LoginRequest;
import com.myspringapp.carsrentalstore.pojo.MessageResponse;
import com.myspringapp.carsrentalstore.pojo.SignUpRequest;
import com.myspringapp.carsrentalstore.repository.RoleRepository;
import com.myspringapp.carsrentalstore.repository.UserRepository;
import com.myspringapp.carsrentalstore.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignUpRequest signupRequest) {

        if (userRepository.existsByUserName(signupRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: This username is exist"));
        }

        User user = new User(signupRequest.getFirstName(), signupRequest.getLastName(),
                signupRequest.getUserName(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

            if (reqRoles.iterator().next().equals("")) {
                roles=reqRoles.stream().map(r->roleRepository.findByName(ERole.USER).orElseThrow())
                        .collect(Collectors.toSet());
            } else {
                roles = reqRoles.stream()
                        .map(role -> roleRepository.findByName(ERole.valueOf(role.toUpperCase()))
                                .orElseThrow(() -> new RuntimeException("Error, this Role is not found")))
                        .peek(System.out::println)
                        .collect(Collectors.toSet());
            }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}

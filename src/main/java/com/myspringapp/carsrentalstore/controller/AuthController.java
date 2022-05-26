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
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
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
import java.util.Set;
import java.util.stream.Collectors;
@Log4j2
@RestController
@RequestMapping("/v1/api/auth")
@CrossOrigin(origins = "*",maxAge = 3600)
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

    @PostMapping("/v1/signin")
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

    @PostMapping("/v1/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signupRequest) {

        if (userRepository.existsByUserName(signupRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: This username is exist"));
        }

        User user = new User(signupRequest.getFirstName(),signupRequest.getLastName(),
                signupRequest.getUserName(),signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "admin":
                        Role adminRole = roleRepository
                                .findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);

                        break;
                    case "oper":
                        Role operRole = roleRepository
                                .findByName(ERole.OPERATOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role OPERATOR is not found"));
                        roles.add(operRole);

                        break;

                    default:
                        Role userRole = roleRepository
                                .findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}

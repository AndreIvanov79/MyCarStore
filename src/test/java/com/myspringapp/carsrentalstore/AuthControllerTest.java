package com.myspringapp.carsrentalstore;

import com.myspringapp.carsrentalstore.controller.AuthController;
import com.myspringapp.carsrentalstore.pojo.LoginRequest;
import com.myspringapp.carsrentalstore.pojo.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    AuthController authController;

    @Spy
    SignUpRequest signupRequest;

    @Spy
    LoginRequest loginRequest;

    @Test
    @DisplayName("Test User registration")
    public void registerUserTest(){
        authController.registerUser(signupRequest);
        verify(authController).registerUser(signupRequest);
    }

    @Test
    @DisplayName("Test User authentication")
    public void authUserTest(){
        authController.authUser(loginRequest);
        verify(authController).authUser(loginRequest);
    }
}

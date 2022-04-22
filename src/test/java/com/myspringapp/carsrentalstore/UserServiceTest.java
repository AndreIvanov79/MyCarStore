package com.myspringapp.carsrentalstore;

import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserServiceImpl userService= Mockito.mock(UserServiceImpl.class);

    @Spy
    User user;

    @Test
    public void getUserListTest() {
        userService.getAllUsers();
        verify(userService).getAllUsers();
        assertNotNull(userService.getAllUsers());
    }

    @Test
    public void getUserByIdTest(){
        userService.getUserById(1);
        verify(userService).getUserById(1);
    }

    @Test
    public void createUserTest(){
       userService.saveOrUpdateUser(user);
       verify(userService).saveOrUpdateUser(user);
    }

    @Test
    public void deleteUserTest(){
        userService.deleteUser(user.getId());
        verify(userService).deleteUser(user.getId());
    }
}


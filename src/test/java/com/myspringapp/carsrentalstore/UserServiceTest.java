package com.myspringapp.carsrentalstore;

import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);

    @Spy
    User user;

    @Spy
    List<User> testUsers = new ArrayList<>();

    @BeforeEach
    public void populateList() {
        testUsers.add(new User("Bill", "Brown", "user1", "bill@mail.com", "user1"));
        testUsers.add(new User("Adam", "Arrow", "user2", "adam@mail.com", "user2"));
        testUsers.add(new User("Dillon", "Dalton", "user3", "dill@mail.com", "user3"));
    }

    @Test
    public void getUserListTest() {
        userService.getAllUsers();
        verify(userService).getAllUsers();
        assertNotNull(userService.getAllUsers());

        when(userService.getAllUsers()).thenReturn(testUsers);
        Assertions.assertEquals(testUsers, userService.getAllUsers());
    }

    @Test
    public void getUserByIdTest() {
        Optional<User> user1 = Optional.ofNullable(testUsers.get(0));

        userService.getUserById(1);
        verify(userService).getUserById(1);

        when(userService.getUserById(1)).thenReturn(user1);
        Assertions.assertEquals(user1, userService.getUserById(1));
    }

    @Test
    public void createUserTest() {
        User newUser = testUsers.get(1);

        userService.saveOrUpdateUser(user);
        verify(userService).saveOrUpdateUser(user);

        when(userService.saveOrUpdateUser(user)).thenReturn(newUser);
        Assertions.assertEquals(newUser, userService.saveOrUpdateUser(user));
    }

    @Test
    public void deleteUserTest() {
        userService.deleteUser(user.getId());
        verify(userService).deleteUser(user.getId());

        when(userService.deleteUser(3)).thenReturn(true);
        Assertions.assertTrue(userService.deleteUser(3));
    }
}


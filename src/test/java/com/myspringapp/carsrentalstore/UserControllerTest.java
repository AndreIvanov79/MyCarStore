package com.myspringapp.carsrentalstore;

import com.myspringapp.carsrentalstore.controller.UserController;
import com.myspringapp.carsrentalstore.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserController userController;

    @Spy
    User user;

    @Test
    @DisplayName("Test GetRequest UserController")
    public void getRequestTest(){
        userController.getUserById(1L);
        verify(userController).getUserById(1L);

        userController.getAllUsers();
        verify(userController).getAllUsers();

        userController.getAllUsersRents(user.getId());
        verify(userController).getAllUsersRents(user.getId());

        userController.getUsersRole(user.getId());
        verify(userController).getUsersRole(user.getId());

        userController.setOfUsersRole(user.getId());
        verify(userController).setOfUsersRole(user.getId());
    }

    @Test
    @DisplayName("Test PostRequest UserController")
    public void postRequestTest(){
        userController.deleteUser(user.getId());
        verify(userController).deleteUser(user.getId());

        userController.saveOrUpdateUser(user);
        verify(userController).saveOrUpdateUser(user);
    }

}

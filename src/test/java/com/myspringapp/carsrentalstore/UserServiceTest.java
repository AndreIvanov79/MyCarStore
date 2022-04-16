package com.myspringapp.carsrentalstore;

import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.repository.UserRepository;
import com.myspringapp.carsrentalstore.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUserListTest() {
        List<User> list = new ArrayList<>();
        User user1 = new User("John", "Johnson", "user1", "user1@mail.com", "user1");
        User user2 = new User("Smith", "Smithson", "user2", "user2@mail.com", "user2");
        User user3 = new User("Bill", "Billson", "user3", "user3@mail.com", "user3");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        when(userRepository.findAll()).thenReturn(list);

        List<User> userList = userService.getAllUsers();

        assertEquals(3, userList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserByIdTest(){
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(new User("John", "Johnson", "user1", "user1@mail.com", "user1")));

        Optional<User> user=userService.getUserById(1L);

        assertEquals("John", user.get().getFirstName());
        assertEquals("Johnson", user.get().getLastName());
        assertEquals("user1" , user.get().getUserName());
        assertEquals("user1@mail.com", user.get().getEmail());
    }

    @Test
    public void createUserTest(){
        User user= new User("Alex","Alexson","user4","user4@mail.com","user4");

        userService.saveOrUpdateUser(user);

        verify(userRepository, times(1)).saveAndFlush(user);
    }
}


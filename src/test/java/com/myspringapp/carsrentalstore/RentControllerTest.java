package com.myspringapp.carsrentalstore;

import com.myspringapp.carsrentalstore.controller.RentController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RentControllerTest {

    @Mock
    RentController rentController;

    @Test
    public void getRequestTest(){
        rentController.getAllRents();
        verify(rentController).getAllRents();

        rentController.getRentById(1L);
        verify(rentController).getRentById(1L);
    }
}

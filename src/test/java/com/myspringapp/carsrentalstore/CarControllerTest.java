package com.myspringapp.carsrentalstore;

import com.myspringapp.carsrentalstore.controller.CarController;
import com.myspringapp.carsrentalstore.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @Mock
    CarController carController;

    @Spy
    Car car;

    @Test
    public void getRequestTest(){
        carController.getAllCars();
        verify(carController).getAllCars();

        carController.getCarById(1L);
        verify(carController).getCarById(1L);
    }
}

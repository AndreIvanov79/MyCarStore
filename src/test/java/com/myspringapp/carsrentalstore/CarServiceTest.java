package com.myspringapp.carsrentalstore;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.model.Vehicle;
import com.myspringapp.carsrentalstore.repository.CarRepository;
import com.myspringapp.carsrentalstore.service.CarServiceImpl;
import com.myspringapp.carsrentalstore.service.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    CarServiceImpl carService;

    @Spy
    Car car;

    @Test
    @DisplayName("Test getAllCars")
    public void geCarListTest() {
       carService.getAllCars();
        verify(carService).getAllCars();
    }

    @Test
    @DisplayName("Test getCarByID")
    public void getCarByIdTest(){
        carService.getCarById(1);
        verify(carService).getCarById(1);
    }

    @Test
    @DisplayName("Test createCar")
    public void createCarTest(){
        carService.saveOrUpdate(car);
        verify(carService).saveOrUpdate(car);
    }

    @Test
    @DisplayName("Test deleteCar")
    public void deleteCarTest(){
        carService.deleteCar(car);
        verify(carService).deleteCar(car);
    }
}

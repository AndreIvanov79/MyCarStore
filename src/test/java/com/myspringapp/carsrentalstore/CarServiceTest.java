package com.myspringapp.carsrentalstore;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.model.Vehicle;
import com.myspringapp.carsrentalstore.repository.CarRepository;
import com.myspringapp.carsrentalstore.service.CarServiceImpl;
import com.myspringapp.carsrentalstore.service.VehicleServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Autowired
    VehicleServiceImpl vehicleService;

    @InjectMocks
    CarServiceImpl carService;

    @Mock
    CarRepository carRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void geCarListTest() {
        List<Car> list = new ArrayList<>();
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        list.add(car1);
        list.add(car2);
        list.add(car3);

        when(carRepository.findAll()).thenReturn(list);

        List<Car> carList = carService.getAllCars();

        assertEquals(3, carList.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void getCarByIdTest(){
        Optional<Vehicle> vehicle= vehicleService.getVehicleById(1);
        Optional<Car> car= carService.getCarById(1);
        when(carRepository.findById(1L)).thenReturn(car);

       // Optional<Car> car=carService.getCarById(1L);

        assertEquals("Volvo", car.get().getBrand());
        assertEquals(1, car.get().getVehicleId());
        assertEquals("MD 909" , car.get().getNumber());
        assertFalse(car.get().isRented());
    }

    @Test
    public void createCarTest(){
        Car car= new Car();

        carService.saveOrUpdate(car);

        verify(carRepository, times(1)).saveAndFlush(car);
    }
}

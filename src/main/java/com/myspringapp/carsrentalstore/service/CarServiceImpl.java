package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(long id){
        return carRepository.findById(id);
    }

    public Optional<Car> getCarByNumber(String number){
        return carRepository.findByNumber(number);
    }

    public void saveOrUpdate(Car car){
        carRepository.saveAndFlush(car);
    }

    public void deleteCar(Car car){
        carRepository.delete(car);
    }
}

package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.Rent;
import com.myspringapp.carsrentalstore.repository.CarRepository;
import com.myspringapp.carsrentalstore.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentRepository rentRepository;

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

    public Car getUsersCars(long id){
        Optional<Rent> rents=rentRepository.getUsersRents(id);
        Car car=rents.get().getCarId();
        return car;
    }
}

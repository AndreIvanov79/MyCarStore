package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
public class CarServiceImpl {
    //Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
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

    public Optional<Car> getUsersCars(long id){
        return carRepository.getCarsOfUserById(id);
    }

    public List<Car> getListRentedCars(){
        return carRepository.getAllRentedCars();
    }

    public List<Car> getListFreeCars(){
        return carRepository.getAllFreeCars();
    }

    public Map<Boolean,List<Car>> getListOfCarsByRentedFlag(Stream<Car> carStream){
        return carStream.collect(groupingBy(car -> car.isRented()));
    }
}

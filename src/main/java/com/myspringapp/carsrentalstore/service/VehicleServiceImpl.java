package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.Price;
import com.myspringapp.carsrentalstore.model.Vehicle;
import com.myspringapp.carsrentalstore.repository.VehicleRepository;
import jdk.jfr.Label;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Log4j2
@Service
public class VehicleServiceImpl {
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(long id){
        return vehicleRepository.findById(id);
    }

    public Optional<Vehicle> getVehicleByName(String name){
        return vehicleRepository.findByName(name);
    }

    public void saveOrUpdate(Vehicle vehicle){
        vehicleRepository.saveAndFlush(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle){
        vehicleRepository.delete(vehicle);
    }

    public Vehicle getTypeOfCar(long id){
        return vehicleRepository.getVehicleTypeOfCar(id);
    }
}

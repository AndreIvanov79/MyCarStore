package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.Price;
import com.myspringapp.carsrentalstore.model.Rent;
import com.myspringapp.carsrentalstore.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl {
    @Autowired
    private RentRepository rentRepository;

    public List<Rent> getAllRents(){
        return rentRepository.findAll();
    }

    public Optional<Rent> getRentById(long id){
        return rentRepository.findById(id);
    }

    public void saveOrUpdate(Rent rent){
        rentRepository.saveAndFlush(rent);
    }

    public void deletePrice(Rent rent){
        rentRepository.delete(rent);
    }
}

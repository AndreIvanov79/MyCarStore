package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.Price;
import com.myspringapp.carsrentalstore.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl {

    @Autowired
    private PriceRepository priceRepository;

    public List<Price> getAllPrices(){
        return priceRepository.findAll();
    }

    public Optional<Price> getCarById(long id){
        return priceRepository.findById(id);
    }

    public Optional<Car> getCarByName(String name){
        return priceRepository.findByName(name);
    }

    public void saveOrUpdate(Price price){
        priceRepository.saveAndFlush(price);
    }

    public void deletePrice(Price price){
        priceRepository.delete(price);
    }
}

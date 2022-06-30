package com.myspringapp.carsrentalstore.service;

import com.myspringapp.carsrentalstore.model.Rent;
import com.myspringapp.carsrentalstore.repository.RentRepository;
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

    public void deleteRent(Rent rent){
        rentRepository.delete(rent);
    }

    public Optional<Rent> getRentsOfUser(long userId){
        return rentRepository.getUsersRents(userId);
    }

    public List<Rent> getAllFinishedRents(){
        return rentRepository.getAllFinishedRents();
    }

    public List<Rent> getAllCurrentRents(){
        return rentRepository.getAllCurrentRents();
    }

    public Map<Boolean,List<Rent>> getListOfRentsByFinishedFlag(Stream<Rent> rents){
        return rents.collect(groupingBy(rent->rent.isFinished()));
    }
}

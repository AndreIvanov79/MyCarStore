package com.myspringapp.carsrentalstore.repository;

import com.myspringapp.carsrentalstore.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findByNumber(String number);
}

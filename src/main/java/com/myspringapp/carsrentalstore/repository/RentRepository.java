package com.myspringapp.carsrentalstore.repository;

import com.myspringapp.carsrentalstore.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {
}

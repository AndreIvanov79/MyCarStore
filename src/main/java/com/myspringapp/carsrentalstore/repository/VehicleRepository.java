package com.myspringapp.carsrentalstore.repository;

import com.myspringapp.carsrentalstore.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle> findByName(String name);

    @Query("select v.name from Vehicle v join Car c where c.id=:id")
    String getVehicleTypeOfCar(@Param("id") long id);
}

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

    @Query("select c.vehicleId from Car c where c.id=:id")
    Vehicle getVehicleTypeOfCar(@Param("id") long id);
}

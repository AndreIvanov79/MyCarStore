package com.myspringapp.carsrentalstore.repository;

import com.myspringapp.carsrentalstore.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findByNumber(String number);

    //@Query("select c from Car c join Rent r where r.userId=:id")
    @Query("select r.carId from Rent r where r.userId.id=:id")
    Optional<Car> getCarsOfUserById(@Param("id") long id);

    @Query("select c from Car c where c.isRented=true ")
    List<Car> getAllRentedCars();

    @Query("select c from Car c where c.isRented=false ")
    List<Car> getAllFreeCars();
}

package com.myspringapp.carsrentalstore.repository;

import com.myspringapp.carsrentalstore.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {

    @Query("select r from Rent r where r.userId.id=:id")
    Optional<Rent> getUsersRents(@Param("id") long id);

    @Query("select r from Rent  r where r.isFinished=true ")
    List<Rent> getAllFinishedRents();

    @Query("select r from Rent  r where r.isFinished=false ")
    List<Rent> getAllCurrentRents();
}

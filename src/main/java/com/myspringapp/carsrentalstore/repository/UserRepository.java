package com.myspringapp.carsrentalstore.repository;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.Rent;
import com.myspringapp.carsrentalstore.model.Role;
import com.myspringapp.carsrentalstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);

    @Query("select r from Rent r where r.userId.id=:id")
    Optional<Rent> getUsersRents(@Param("id") long id);
}

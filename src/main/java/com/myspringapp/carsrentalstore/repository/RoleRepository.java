package com.myspringapp.carsrentalstore.repository;

import com.myspringapp.carsrentalstore.model.ERole;
import com.myspringapp.carsrentalstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}

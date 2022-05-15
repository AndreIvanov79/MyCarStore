package com.myspringapp.carsrentalstore.repository;

import com.myspringapp.carsrentalstore.model.ERole;
import com.myspringapp.carsrentalstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);

    @Query("select r from Role r join User u where u.id=:id")
    Optional<Role> getUsersRole(@Param("id") long id);
}

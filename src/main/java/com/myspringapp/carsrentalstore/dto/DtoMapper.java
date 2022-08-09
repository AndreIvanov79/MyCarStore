package com.myspringapp.carsrentalstore.dto;

import com.myspringapp.carsrentalstore.model.Car;
import com.myspringapp.carsrentalstore.model.Rent;
import com.myspringapp.carsrentalstore.model.User;
import com.myspringapp.carsrentalstore.repository.RentRepository;
import com.myspringapp.carsrentalstore.service.CarServiceImpl;
import com.myspringapp.carsrentalstore.service.RentServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class DtoMapper {
    @Autowired
    private RentServiceImpl rentService;

    public UserDTO toUserDto(User user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRents(rentService.getRentsOfUser(user.getId()).stream().map(rent -> rent.getId())
                .collect(Collectors.toList()));
        return userDTO;

    }

    public CarDTO toCarDto(Car car) {
        CarDTO carDTO=new CarDTO();
        carDTO.setBrand(car.getBrand());
        carDTO.setType(car.getVehicleId().getName());
        carDTO.setNumber(car.getNumber());
        carDTO.setRented(String.valueOf(car.isRented()));

        return carDTO;
    }

    public RentDTO toRentDto(Rent rent) {
        RentDTO rentDTO=new RentDTO();
        rentDTO.setUser(rent.getUserId().getUserName());
        rentDTO.setCar(rent.getCarId().getBrand().concat("- ").concat(rent.getCarId().getNumber()));
        rentDTO.setStartDate(rent.getStartDate().toString());
        rentDTO.setEndDate(rent.getEndDate().toString());
        rentDTO.setFinished(String.valueOf(rent.isFinished()));

        return rentDTO;
    }
}

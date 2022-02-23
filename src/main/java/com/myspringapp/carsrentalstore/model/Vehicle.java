package com.myspringapp.carsrentalstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private Price priceId;

    @Column(name = "initial_period")
    private int initPeriod;

}
